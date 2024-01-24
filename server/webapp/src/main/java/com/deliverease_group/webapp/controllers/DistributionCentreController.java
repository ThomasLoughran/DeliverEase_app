package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.services.DistributionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/distribution-centres")
public class DistributionCentreController {

    // TODO  - POST create routes, GET routes by driverId, GET all distribution centres

    @Autowired
    DistributionService distributionService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<DistributionCentre>> getAllDistributionCentres() {
        return distributionService.findAllDistributionCentres();
    }

    
}
