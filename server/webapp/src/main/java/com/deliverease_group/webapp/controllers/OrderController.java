package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Order;
import com.deliverease_group.webapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrdersByDistributionCentre(@RequestParam Long distCentreId){
        return new ResponseEntity<>(orderService.getAllOrdersByDistributionCentre(distCentreId), HttpStatus.OK );
    }

    @GetMapping("/completion")
    public ResponseEntity<List<Order>> getDistributionCentreOrdersByCompletionStatus(@RequestParam Long distCentreId,@RequestParam boolean isOrderComplete){
        return new ResponseEntity<>(orderService.getDistributionCentreOrdersByCompletionStatus(distCentreId, isOrderComplete), HttpStatus.OK );
    }

    // TODO - GET all orders by distribution centre and date, GET all orders by driver ID and date, GET all incomplete orders,
    //          PATCH order message, PATCH update isComplete, Patch update is ManagerReviewed
}
