package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.models.*;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.OrderRepository;
import com.deliverease_group.webapp.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import static java.time.ZoneOffset.UTC;

@Service
public class RouteService {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DistributionCentreRepository distributionCentreRepository;

    public List<Route> findAllRoutesByDistCentreIdAndDate(Long distCentreId, LocalDate date){
        return routeRepository.findAllByDistributionCentreIdAndDate(distCentreId, ZonedDateTime.of(date, date.atStartOfDay().toLocalTime(), UTC));
    }

    public List<Route> findAllRoutesByDistCentreIdAndIsComplete(Long distCentreId, boolean isComplete){
        return routeRepository.findAllRoutesByDistributionCentreIdAndIsComplete(distCentreId, isComplete);
    }

    public List<Order> getDistributionCentreOrdersByCompletionStatus(Long distCentreId, boolean isOrderComplete) {
        return orderRepository.findAllByDistributionCentreIdAndIsCompletedOrderByDateOrdered(distCentreId,isOrderComplete);
    }


    public List<Order> generateRoutes(Long distCentreId, LocalDate localDate) {

        List<Order> incompleteOrders = getDistributionCentreOrdersByCompletionStatus(distCentreId, false);
        List<Driver> availableDrivers =  driverRepository.availableDrivers(distCentreId, localDate);
        List<Route> alreadyAssignedRoutes = findAllRoutesByDistCentreIdAndDate(distCentreId, localDate);

        if (!alreadyAssignedRoutes.isEmpty()){
            for (Route route : alreadyAssignedRoutes){
                Optional<Driver> optionalDriver = driverRepository.findById(route.getDriverId());
                //remove drivers already on route that date
                if (optionalDriver.isPresent()){
                    Driver driver = optionalDriver.get();
                    // Check if the driver is present in availableDrivers before removing
                    if (availableDrivers.contains(driver)) {
                        availableDrivers.remove(driver);
                    }
                }

                //remove orders already on route that date
                for (Long orderId: route.getOrderId()){
                    Optional<Order> optionalOrder = orderRepository.findById(orderId);
                    //remove orders already on route that date
                    if (optionalOrder.isPresent()){
                        Order order = optionalOrder.get();
                        // Check if the order is present in incompleteOrders before removing
                        if (incompleteOrders.contains(order)) {
                            incompleteOrders.remove(order);
                        }
                    }
                }
            }
        }

        //drivers removed if already on route that day
        //orders removed if already on route that day OR in an incomplete route

        alreadyAssignedRoutes = findAllRoutesByDistCentreIdAndIsComplete(distCentreId,false);
        if (!alreadyAssignedRoutes.isEmpty()){
            for (Route route : alreadyAssignedRoutes){
                //remove orders already on incomplete routes
                for ( Long orderId: route.getOrderId()){
                    Optional<Order> optionalOrder = orderRepository.findById(orderId);
                    //remove orders already on route that date
                    if (optionalOrder.isPresent()){
                        Order order = optionalOrder.get();
                        // Check if the order is present in incompleteOrders before removing
                        if (incompleteOrders.contains(order)) {
                            incompleteOrders.remove(order);
                        }
                    }
                }
            }
        }

//        cannot create routes if no drivers available
        if (availableDrivers.isEmpty() || incompleteOrders.isEmpty()){
            return null;
        }

//        assign a list of the most urgent parcels to be delivered that the drivers collectively have capacity for
        int maxParcelsPerVan = 10;
        List<Order> ordersToBeDelivered = setTotalOrders(availableDrivers, incompleteOrders, maxParcelsPerVan);

//        create nodes of all the locations with radial coordinates centred on distCentre
        DistributionCentre distributionCentre = distributionCentreRepository.findById(distCentreId).get();
        List<Node> orderLocations = createNodes( distributionCentre, ordersToBeDelivered);

        //sort order Locations By Angle Theta
        orderLocations = sortNodesByTheta(orderLocations);

//        Assign drivers a list of their orders
        Map<Long,ArrayList<Node>> ordersInRoutes =  assignDriversOrders(availableDrivers, orderLocations, maxParcelsPerVan);

//        id is -1 to avoid overlap with any order id
        Node distributionCentreNode = new Node(distributionCentre.getLocation().getX(), distributionCentre.getLocation().getY(), -1);

        List<Node> orderedNodeList = new ArrayList<>();
        for (Long driverId : ordersInRoutes.keySet()){
            orderedNodeList = routeFind(ordersInRoutes.get(driverId), distributionCentreNode);
            ArrayList<Long> orderedIds = new ArrayList<>();
            for (Node node : orderedNodeList){
//                dist centre has id -1 and must not be included in final list
                if (node.getOrderId() > 0){
                    orderedIds.add(node.getOrderId());
                }
            }
            if (!orderedIds.isEmpty()) {
                Route route = new Route(distributionCentre, orderedIds, driverId, ZonedDateTime.of(localDate, localDate.atStartOfDay().toLocalTime(), UTC), false);
                routeRepository.save(route);
            }
        }

        return ordersToBeDelivered;
    }

    public List<Node> routeFind(ArrayList<Node> nodes, Node distCentre) {
        nodes.add(0, distCentre);
        int numberOfNodes = nodes.size();

//        create a matrix of the distances between two nodes
        double[][] distanceMatrix = new double[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++) {
            for (int j = 0; j < numberOfNodes; j++) {
                double distance = Math.sqrt(Math.pow(nodes.get(i).getX() - nodes.get(j).getX(), 2) + Math.pow(nodes.get(i).getY() - nodes.get(j).getY(), 2));
                distanceMatrix[i][j] = distance;
                distanceMatrix[j][i] = distance;
            }
        }

        ArrayList<Node> nodesInRoute = new ArrayList<>();
        ArrayList<Node> nodesNotInRoute = new ArrayList();
        for (Node node : nodes) {
            nodesNotInRoute.add(node);
        }

        nodesInRoute.add(nodes.get(0));
        nodesNotInRoute.remove(0);

//        iterate through n times until every node is a part of the node tree (1st time is before loop)
        for (int i = 0; i < numberOfNodes - 1; i++) {
            Node routeNode = nodesInRoute.get(i);
            Node nonRouteNode = nodesNotInRoute.get(0);
            Node minNonRouteNode = nodesNotInRoute.get(0);
            double minDistance = distanceMatrix[nodes.indexOf(routeNode)][nodes.indexOf(nonRouteNode)];
//            iterate through the nodes in the node tree
            for (int j = 1; j < nodesNotInRoute.size(); j++) {
                nonRouteNode = nodesNotInRoute.get(j);
                double distance = distanceMatrix[nodes.indexOf(routeNode)][nodes.indexOf(nonRouteNode)];
                if (distance < minDistance) {
                    minDistance = distance;
                    minNonRouteNode = nodesNotInRoute.get(j);
                }
            }

            nodesInRoute.add(nodes.get(nodes.indexOf(minNonRouteNode)));
            nodesNotInRoute.remove(nodes.get(nodes.indexOf(minNonRouteNode)));

        }
        nodesInRoute.add(distCentre);
        return nodesInRoute;
    }

    public List<Order> setTotalOrders(List<Driver> availableDrivers, List<Order> incompleteOrders, int maxParcelsPerVan){
        int totalCapacity = 0;
        int totalWeight = 0;
        int totalOrders = maxParcelsPerVan * availableDrivers.size();

        //gets the totals of each across all drivers
        for (Driver driver : availableDrivers){
            totalCapacity += driver.getVanCapacity();
            totalWeight += driver.getVanMaxWeight();
        }

        //Gets total list of orders up-to any of the above limits
        ArrayList<Order> ordersToBeDelivered = new ArrayList<>();
        int runningTotalCapacity = 0;
        int runningTotalWeight = 0;
        int runningTotalOrders = 0;

        for (Order order : incompleteOrders){
            runningTotalOrders+=1;
            runningTotalWeight+=order.getWeight();
            runningTotalCapacity+=order.getSize();

            if ((runningTotalOrders <= totalOrders)
                    &&(runningTotalWeight < totalWeight)
                    &&(runningTotalCapacity < totalCapacity)){

                ordersToBeDelivered.add(order);

            }else{
                break;
            }
        }
        return ordersToBeDelivered;
    }

    public List<Node> createNodes(DistributionCentre distributionCentre, List<Order> ordersToBeDelivered){

        double distCentreX = distributionCentre.getLocation().getX();
        double distCentreY = distributionCentre.getLocation().getY();

        ArrayList<Node> orderLocations = new ArrayList<>();

        // Gets the order's angle from North of distribution centre and it's radial distance from the distribution centre
        for (Order order : ordersToBeDelivered){
            Node node = new Node(order.getLongitude(),order.getLatitude(),order.getId());

            node.setRadius( Math.sqrt( Math.pow(node.getX() - distCentreX,2) ) +  Math.sqrt( Math.pow(node.getY() - distCentreY,2) ) );
            node.setTheta(Math.atan2(node.getY() - distCentreY, node.getX() - distCentreX));

            orderLocations.add(node);
        }

        return orderLocations;
    }

    public List<Node> sortNodesByTheta(List<Node> nodeList){
        Collections.sort(nodeList, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (Math.pow(10, 6) * (o1.getTheta() - o2.getTheta()));
            }
        });
        return nodeList;
    }

    public Map<Long,ArrayList<Node>> assignDriversOrders(List<Driver> availableDrivers, List<Node> orderLocations, int maxParcelsPerVan){
        //get and shuffle drivers
        Collections.shuffle(availableDrivers);

        Map<Long,ArrayList<Node>> ordersInRoutes = new HashMap<>();

        //sets the all drivers to have an empty list of routes associated with them;
        for (Driver driver : availableDrivers){
            ordersInRoutes.put(driver.getId(), new ArrayList<>());
        }

        // Assign orders to Drivers
        int driverCount = 0;
        Driver currentDriver = availableDrivers.get(driverCount);

        int runningTotalCapacity = 0;
        int runningTotalWeight = 0;
        int runningTotalOrders = 0;
        for (Node node : orderLocations){
            Order order = orderRepository.findById(node.getOrderId()).get();
            runningTotalWeight += order.getWeight();
            runningTotalCapacity += order.getSize();
            runningTotalOrders ++;

            if(runningTotalCapacity < currentDriver.getVanCapacity() &&
                    runningTotalWeight < currentDriver.getVanMaxWeight() &&
                    runningTotalOrders <= maxParcelsPerVan){
                ordersInRoutes.get(currentDriver.getId()).add(node);
            } else {
                driverCount ++;
                if (driverCount < availableDrivers.size()) {
                    currentDriver = availableDrivers.get(driverCount);
                    ordersInRoutes.get(currentDriver.getId()).add(node);
                    runningTotalCapacity = order.getSize();
                    runningTotalWeight = order.getWeight();
                    runningTotalOrders = 1;
                } else {
                    break;
                }
            }
        }
        return ordersInRoutes;
    }


    public Route findRouteById(Long routeId) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()){
            return optionalRoute.get();
        } else {
            return null;
        }
    }

    public Route updateRouteCompletion(Long routeId, boolean isComplete) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()){
            Route route = optionalRoute.get();
            route.setComplete(isComplete);
            routeRepository.save(route);
            return route;
        } else {
            return null;
        }
    }

    public void deleteRouteById(Long routeId) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()){
            Route route = optionalRoute.get();
            routeRepository.delete(route);
        }
    }

    public Route findRouteByDriverIdAndDate(Long driverId, LocalDate localDate) {
        return routeRepository.findRouteByDriverIdAndDate(driverId, ZonedDateTime.of(localDate, localDate.atStartOfDay().toLocalTime(), UTC));
    }
}
