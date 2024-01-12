package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.LoginDTO;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee checkLoginDetails(LoginDTO loginDTO){
        Optional<Employee> optionalEmployee = employeeRepository.findById(loginDTO.getId());
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            if (employee.getPassword().equals(loginDTO.getPassword())){
                return employee;
            }
        }
        return null;
    }

}
