package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.OrderDTO;
import com.deliverease_group.webapp.models.*;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.OrderRepository;
import com.deliverease_group.webapp.repositories.RouteRepository;
import org.aspectj.weaver.ast.Or;
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
        Optional<Order> optionalOrder = orderRepository.findById(orderDTO.getId());
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            order.setIssue(Issue.fromInteger(orderDTO.getIssue()));
            order.setTimeIssuePosted(ZonedDateTime.now());
            order.setManagerReviewed(false);
            order.setCompleted(false);
            orderRepository.save(order);
            return order;
        } else {
            return null;
        }

    }


    public Order updateOrderCompletion(Long id, boolean isComplete) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()){
            Order order = orderRepository.findById(id).get();
            order.setCompleted(isComplete);
            orderRepository.save(order);
            return order;
        } else {
            return null;
        }
    }

    public Order updateOrderManagerReviewed(Long id, boolean isManagerReviewed) {
        Order order = orderRepository.findById(id).get();
        order.setManagerReviewed(isManagerReviewed);
        orderRepository.save(order);
        return order;
    }

    public List<Order> getAllByDistributionAndIsManagerReviewed(Long distCentreId, boolean isManagerReviewed) {
        return orderRepository.findByDistributionCentreIdAndIsManagerReviewedAndIssueIsNotNull(distCentreId, isManagerReviewed);

    }
}
