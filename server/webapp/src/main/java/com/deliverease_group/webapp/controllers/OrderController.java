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
        return new ResponseEntity<>(orderService.updateOrderIssue(orderDTO),HttpStatus.OK);
    }

    @PatchMapping(value = "/complete/{id}")
    public ResponseEntity<Order> patchOrderCompletionById(@PathVariable Long id, @RequestParam boolean isComplete){
        return new ResponseEntity<>(orderService.updateOrderCompletion(id, isComplete),HttpStatus.OK);
    }

    @PatchMapping(value = "/manager-review/{id}")
    public ResponseEntity<Order> patchOrderManagerReviewById(@PathVariable Long id, @RequestParam boolean isManagerReviewed){
        return new ResponseEntity<>(orderService.updateOrderManagerReviewed(id, isManagerReviewed),HttpStatus.OK);
    }

    @PostMapping(value = "/new-routes/{id}")
    public ResponseEntity<List<Order>> generateRoutes (@PathVariable Long distCentreId, @RequestParam LocalDate localDate){
        return new ResponseEntity<>(orderService.generateRoutes(distCentreId,localDate),HttpStatus.CREATED);
    }

    // TODO - GET all orders by distribution centre and date, GET all orders by driver ID and date, GET all incomplete orders,
    //          PATCH order message, PATCH update isComplete, Patch update is ManagerReviewed
}
