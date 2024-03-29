package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.dtos.OrderDTO;
import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Order;
import com.deliverease_group.webapp.services.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrdersByDistributionCentre(@RequestParam Long distCentreId){
        List<Order> orderList =orderService.getAllOrdersByDistributionCentre(distCentreId);
        if (orderList.size()>0) {
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(orderList,HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/completion")
    public ResponseEntity<List<Order>> getDistributionCentreOrdersByCompletionStatus(@RequestParam Long distCentreId,@RequestParam boolean isOrderComplete){
        List<Order> orderList = orderService.getDistributionCentreOrdersByCompletionStatus(distCentreId, isOrderComplete);
        if (orderList.size()>0) {
            return new ResponseEntity<>(orderList, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(orderList,HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Order order = orderService.getOrderById(id);
        if (order!=null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/issue")
    public ResponseEntity<Order> patchOrderIssueById(@RequestBody OrderDTO orderDTO){
        Order order = orderService.updateOrderIssue(orderDTO);
        if (order != null){
            return new ResponseEntity<>(order,HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(value = "/complete/{id}")
    public ResponseEntity<Order> patchOrderCompletionById(@PathVariable Long id, @RequestParam boolean isComplete){
        Order order = orderService.updateOrderCompletion(id, isComplete);
        if (order != null){
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PatchMapping(value = "/manager-review/{id}")
    public ResponseEntity<Order> patchOrderManagerReviewById(@PathVariable Long id, @RequestParam boolean isManagerReviewed){
        Order order = orderService.updateOrderManagerReviewed(id, isManagerReviewed);
        if (order != null){
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping(value = "/issue/all")
    public ResponseEntity<List<Order>> getAllIssuesByManagerReviewed (@RequestParam Long distCentreId, @RequestParam boolean isManagerReviewed){
        return new ResponseEntity<>(orderService.getAllByDistributionAndIsManagerReviewed(distCentreId,isManagerReviewed),HttpStatus.OK);
    }

    // TODO - GET all orders by distribution centre and date, GET all orders by driver ID and date, GET all incomplete orders,
    //          PATCH order message, PATCH update isComplete, Patch update is ManagerReviewed
}
