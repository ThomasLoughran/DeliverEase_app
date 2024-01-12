package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.OrderDTO;
import com.deliverease_group.webapp.models.Issue;
import com.deliverease_group.webapp.models.Order;
import com.deliverease_group.webapp.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.deliverease_group.webapp.models.Issue.LOST_IN_TRANSIT;

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

    public Order updateOrder(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId()).get();

        order.setCompleted(orderDTO.isCompleted());
        order.setManagerReviewed(orderDTO.isManagerReviewed());
        order.setIssue(Issue.fromInteger(orderDTO.getIssue()));
        order.setTimeIssuePosted(orderDTO.getTimeIssuePosted());

        orderRepository.save(order);
        return order;
    }


}
