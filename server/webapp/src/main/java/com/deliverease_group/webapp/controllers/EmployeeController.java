package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/login")
    public ResponseEntity<Employee> getEmployeeByLoginDetail(@RequestParam Long employeeId, @RequestParam String password){
        Employee employee = employeeService.checkLoginDetails(employeeId, password);
        if (employee != null){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
