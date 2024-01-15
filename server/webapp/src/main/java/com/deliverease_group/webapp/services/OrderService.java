package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.OrderDTO;
import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Issue;
import com.deliverease_group.webapp.models.Order;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


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

        return ordersToBeDelivered;

    }
}
