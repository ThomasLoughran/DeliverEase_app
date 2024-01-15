package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.OrderDTO;
import com.deliverease_group.webapp.models.*;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DistributionCentreRepository distributionCentreRepository;

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
        int totalOrders = 50 * availableDrivers.size();

        //gets the totals of each across all drivers
        for (Driver driver : availableDrivers){
            totalCapacity += driver.getVanCapacity();
            totalWeight += driver.getVanMaxWeight();
        }

        //Gets total list of orders up-to any of the above limits
        ArrayList<Order> ordersToBeDelivered = new ArrayList<>();
        int runningTotalCapacity = 0;
        int runningotalWeight = 0;
        int runningotalOrders = 0;

        for (Order order : incompleteOrders){
            runningotalOrders+=1;
            runningotalWeight+=order.getWeight();
            runningTotalCapacity+=order.getSize();

            if ((runningotalOrders < totalOrders)
                    ||(runningotalWeight < totalWeight)
                    ||(runningTotalCapacity < totalCapacity)){

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

        Map<Long,ArrayList<Long>> ordersInRoutes = new HashMap<>();

        //sets the vans as empty;
        for (Driver driver : availableDrivers){
            driver.setCapacityFull(false);
        }

        runningTotalCapacity = 0;
        runningotalWeight = 0;
        Order order;

        //Assign orders to Drivers
        Iterator<Driver> iterator = availableDrivers.iterator();

        for (Node node : orderLocations) {
            order = orderRepository.findById(node.getOrderId()).orElse(null);

            
            iterator = availableDrivers.iterator(); // Reset the iterator for each order

            while (iterator.hasNext()) {
                Driver driver = iterator.next();
                runningotalWeight += order.getWeight();
                runningTotalCapacity += order.getSize();

                if (
                        (driver.getVanCapacity() < runningTotalCapacity) ||
                                (driver.getVanMaxWeight() < runningotalWeight) ||
                                (ordersInRoutes.get(driver.getId()).size() < 50)
                ) {
                    // Handle the case where the driver is not valid for the order
                } else {
                    iterator.remove();
                    runningTotalCapacity = 0;
                    runningotalWeight = 0;
                }
            }
        }







        return ordersToBeDelivered;

    }
}
