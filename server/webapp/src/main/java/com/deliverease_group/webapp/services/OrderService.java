package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.OrderDTO;
import com.deliverease_group.webapp.models.*;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.OrderRepository;
import com.deliverease_group.webapp.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;

import static java.time.ZoneOffset.UTC;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DistributionCentreRepository distributionCentreRepository;

    @Autowired
    RouteRepository routeRepository;

    public List<Order> getAllOrdersByDistributionCentre(Long distCentreId){
        return orderRepository.findAllByDistributionCentreId(distCentreId);
    }

    public List<Order> getDistributionCentreOrdersByCompletionStatus(Long distCentreId, boolean isOrderComplete) {
        return orderRepository.findAllByDistributionCentreIdAndIsCompletedOrderByDateOrdered(distCentreId,isOrderComplete);
    }

    public Order getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            return optionalOrder.get();
        }
        return null;
    }

    public Order updateOrderIssue(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId()).get();
        order.setIssue(Issue.fromInteger(orderDTO.getIssue()));
        order.setTimeIssuePosted(orderDTO.getTimeIssuePosted());
        order.setManagerReviewed(false);

        orderRepository.save(order);
        return order;
    }


    public Order updateOrderCompletion(Long id, boolean isComplete) {
        Order order = orderRepository.findById(id).get();
        order.setCompleted(isComplete);
        orderRepository.save(order);
        return order;
    }

    public Order updateOrderManagerReviewed(Long id, boolean isManagerReviewed) {
        Order order = orderRepository.findById(id).get();
        order.setManagerReviewed(isManagerReviewed);
        orderRepository.save(order);
        return order;
    }

    public List<Order> generateRoutes(Long distCentreId, LocalDate localDate) {
        List<Order> incompleteOrders = getDistributionCentreOrdersByCompletionStatus(distCentreId, false);

        List<Driver> availableDrivers =  driverRepository.availableDrivers(distCentreId, localDate);

        int totalCapacity = 0;
        int totalWeight = 0;
        int maxParcelsPerVan = 3;
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

        DistributionCentre distributionCentre = distributionCentreRepository.findById(distCentreId).get();

        double distCentreX = distributionCentre.getLocation().getX();
        double distCentreY = distributionCentre.getLocation().getY();

        ArrayList<Node> orderLocations = new ArrayList<>();

        // Gets the order's angle from North of distribution centre and it's radial distance from the distribution centre
        for (Order order : ordersToBeDelivered){
            Node node = new Node(order.getLongitude(),order.getLatitude(),order.getId());

            node.setRadius( Math.sqrt( Math.pow(node.getX() - distCentreX,2) ) +  Math.sqrt( Math.pow(node.getY() - distCentreY,2) ) );
            node.setTheta(Math.atan(node.getY())/ node.getX());

            orderLocations.add(node);
        }

        //sort order Locations By Angle Theta
        Collections.sort(orderLocations, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return (int) (o1.getTheta() - o2.getTheta());
            }
        });

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

        runningTotalCapacity = 0;
        runningTotalWeight = 0;
        runningTotalOrders = 0;
        for (Node node : orderLocations){
            Order order = orderRepository.findById(node.getOrderId()).get();
            runningTotalWeight += order.getWeight();
            runningTotalCapacity += order.getSize();
            runningTotalOrders ++;

            if(runningTotalCapacity < currentDriver.getVanCapacity() &&
            runningTotalWeight < currentDriver.getVanMaxWeight() &&
            runningTotalOrders < maxParcelsPerVan){
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

//        id is -1 to avoid overlap with any order id
        Node distributionCentreNode = new Node(distCentreX, distCentreY, -1);

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
            Route route = new Route(distributionCentre, orderedIds, driverId, ZonedDateTime.of(localDate, localDate.atStartOfDay().toLocalTime(), UTC), false);
            routeRepository.save(route);
        }

        return ordersToBeDelivered;

    }

    public List<Node> routeFind(ArrayList<Node> nodes, Node distCentre){
        nodes.add(0, distCentre);
        int numberOfNodes = nodes.size();

//        create a matrix of the distances between two nodes
        double[][] distanceMatrix = new double[numberOfNodes][numberOfNodes];
        for (int i = 0; i < numberOfNodes; i++){
            for (int j = 0; j < numberOfNodes; j++){
                double distance = Math.sqrt(Math.pow(nodes.get(i).getX() - nodes.get(j).getX(), 2) + Math.pow(nodes.get(i).getY() - nodes.get(j).getY(), 2));
                distanceMatrix[i][j] = distance;
                distanceMatrix[j][i] = distance;
            }
        }

//        make a copy of the arraylist
        ArrayList<Node> nodesInRoute = new ArrayList<>();
        ArrayList<Node> nodesNotInRoute = new ArrayList<>();
        for (Node node : nodes){
            nodesNotInRoute.add(node);
        }

        nodesInRoute.add(nodes.get(0));
        nodesNotInRoute.remove(0);

//        iterate through n times until every node is a part of the node tree (1st time is before loop)
        for (int i = 0; i < numberOfNodes - 1; i++){
            Node routeNode = nodesInRoute.get(i);
            Node minRouteNode = nodesInRoute.get(i);
            Node nonRouteNode = nodesNotInRoute.get(0);
            Node minNonRouteNode = nodesNotInRoute.get(0);
            double minDistance = distanceMatrix[nodesInRoute.indexOf(routeNode)][nodesNotInRoute.indexOf(nonRouteNode)];
//            iterate through the nodes in the node tree
            for (int j = 1; j < nodesNotInRoute.size(); j++){
                nonRouteNode = nodesNotInRoute.get(j);
                double distance = distanceMatrix[nodesInRoute.indexOf(routeNode)][nodesNotInRoute.indexOf(nonRouteNode)];
                if (distance < minDistance){
                    minDistance = distance;
                    minRouteNode = nodesInRoute.get(i);
                    minNonRouteNode = nodesNotInRoute.get(j);
                }
            }


            nodesInRoute.add(minNonRouteNode);
            nodesNotInRoute.remove(minNonRouteNode);

        }



//        return nodeList back to original form
        double[][] driverRoute = new double[numberOfNodes + 1][2];
        for (int i = 0; i < numberOfNodes; i++){
            driverRoute[i][0] = nodesInRoute.get(i).getX();
            driverRoute[i][1] = nodesInRoute.get(i).getY();
        }

//        loop back to start
        driverRoute[driverRoute.length - 1][0] = nodesInRoute.get(0).getX();
        driverRoute[driverRoute.length - 1][1] = nodesInRoute.get(0).getY();

//        for (int i = 0; i < driverRoute.length; i++){
//            System.out.println("(" + driverRoute[i][0] + ", " + driverRoute[i][1] + ")");
//
//        }

//        adding distribution centre as the last element in the route
        nodesInRoute.add(distCentre);
        return nodesInRoute;
    }
}
