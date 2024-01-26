package com.deliverease_group.webapp.controllers;

import com.deliverease_group.webapp.dtos.LoginDTO;
import com.deliverease_group.webapp.dtos.PasswordDTO;
import com.deliverease_group.webapp.dtos.UpdatePasswordResponseDTO;
import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/login")
    public ResponseEntity<Employee> getEmployeeByLoginDetail(@RequestBody LoginDTO loginDTO){
        Employee employee = employeeService.checkLoginDetails(loginDTO);
        if (employee != null){
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/update-password")
    public ResponseEntity<UpdatePasswordResponseDTO> updatePassword(@RequestBody PasswordDTO passwordDTO){
        UpdatePasswordResponseDTO updatePasswordResponseDTO = employeeService.updatePassword(passwordDTO);
        if (updatePasswordResponseDTO == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (updatePasswordResponseDTO.getResponse().equals("Password updated successfully")) {
            return new ResponseEntity<>(updatePasswordResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatePasswordResponseDTO,HttpStatus.BAD_REQUEST);
    }

}
