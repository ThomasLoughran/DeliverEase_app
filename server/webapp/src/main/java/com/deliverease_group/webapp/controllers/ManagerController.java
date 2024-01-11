package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers(){
            return new ResponseEntity<>(managerService.findAllDrivers(), HttpStatus.OK);

    }


}
