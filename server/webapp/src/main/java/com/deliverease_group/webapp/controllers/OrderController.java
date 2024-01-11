package com.deliverease_group.webapp.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class OrderController {

    // TODO - GET all orders by distribution centre and date, GET all orders by driver ID and date, GET all incomplete orders,
    //          PATCH order message, PATCH update isComplete, Patch update is ManagerReviewed
}
