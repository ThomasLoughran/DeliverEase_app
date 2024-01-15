package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Manager;
import com.deliverease_group.webapp.models.Role;
import com.deliverease_group.webapp.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/drivers")

public class DriverController {

    // TODO  - GET routes by driverId, PATCH update availability, PATCH order is successful/unsuccessful, * PATCH change distribution centre (EXTENSION) *

    @Autowired
    DriverService driverService;


    @GetMapping
    public ResponseEntity<List<Employee>> getAllDriversByDistCentId(@RequestParam Long distCentId) {
        try {
            return new ResponseEntity<>(driverService.findAllDriverIdsByDistCentId(distCentId, Role.DRIVER), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<List<Driver>> getAvailableDriversByDistCentId(@RequestParam Long distCentId, @RequestParam LocalDate date) {

        System.out.println("test");
        System.out.println(date);
        return new ResponseEntity<>(driverService.findAllAvailableDrivers(distCentId, date), HttpStatus.OK);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Long id){
        Driver driver = driverService.getDriverById(id);
        if (driver != null){
            return new ResponseEntity<>(driver, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getAllDrivers(){
        return new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
    }

    @PatchMapping("/change-availablec/{id}")
    public ResponseEntity<Driver> updateDriverAvailability(@PathVariable Long id, @RequestParam LocalDate date){
        return new ResponseEntity<>(driverService.updateDriverAvailability(id, date), HttpStatus.OK);
    }


}
