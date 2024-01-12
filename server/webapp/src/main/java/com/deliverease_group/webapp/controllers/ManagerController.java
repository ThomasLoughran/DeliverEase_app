package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Manager;
import com.deliverease_group.webapp.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id){
        Manager manager = managerService.getManagerById(id);
        if (manager != null){
            return new ResponseEntity<>(manager, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Manager>> getAllManagers(){
        return new ResponseEntity<>(managerService.getAllManagers(), HttpStatus.OK);
    }



    // TODO - PATCH assign driver to route, GET all active messages by centre id, GET all routes by date

}
