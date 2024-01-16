package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.Order;
import com.deliverease_group.webapp.services.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    RouteService routeService;

    @PostMapping(value = "/new-routes/{distCentreId}")
    public ResponseEntity<List<Order>> generateRoutes (@PathVariable Long distCentreId, @RequestParam LocalDate localDate){
        List<Order> orderList = routeService.generateRoutes(distCentreId,localDate);
        if (orderList!=null) {
            return new ResponseEntity<>(orderList, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }

    }

}
