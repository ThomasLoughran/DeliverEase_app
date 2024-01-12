package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee checkLoginDetails(Long employeeId, String password){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isPresent()){
            Employee employee = optionalEmployee.get();
            if (employee.getPassword().equals(password)){
                return employee;
            }
        }
        return null;
    }

}
