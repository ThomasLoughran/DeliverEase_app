package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.OrderDTO;
import com.deliverease_group.webapp.models.Issue;
import com.deliverease_group.webapp.models.Order;
import com.deliverease_group.webapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrdersByDistributionCentre(Long distCentreId){
        return orderRepository.findAllByDistributionCentreId(distCentreId);
    }

    public List<Order> getDistributionCentreOrdersByCompletionStatus(Long distCentreId, boolean isOrderComplete) {
        return orderRepository.findAllByDistributionCentreIdAndIsCompleted(distCentreId,isOrderComplete);
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
}
