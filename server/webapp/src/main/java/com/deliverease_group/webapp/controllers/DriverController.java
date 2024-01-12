package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Role;
import com.deliverease_group.webapp.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")

public class DriverController {

    // TODO  - GET routes by driverId, PATCH update availability, PATCH order is successful/unsuccessful, * PATCH change distribution centre (EXTENSION) *

    @Autowired
    DriverService driverService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllDriversByDistCentId(@RequestParam Long distCentId){
        try {
            return new ResponseEntity<>(driverService.findAllDriverIdsByDistCentId(distCentId, Role.DRIVER), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



}
