package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.LoginDTO;
import com.deliverease_group.webapp.dtos.PasswordDTO;
import com.deliverease_group.webapp.dtos.MessageResponseDTO;
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

    public MessageResponseDTO updatePassword(PasswordDTO passwordDTO) {
        MessageResponseDTO messageResponseDTO = new MessageResponseDTO();
        Optional<Employee> optionalEmployee = employeeRepository.findById(passwordDTO.getId());
        if (optionalEmployee.isEmpty()){
            return null;
        }
        Employee employee = optionalEmployee.get();
        if (!employee.getPassword().equals(passwordDTO.getOldPassword())){
            messageResponseDTO.setResponse("Old password doesn't match");
            return messageResponseDTO;
        }
        if (employee.getPassword().equals(passwordDTO.getNewPassword())){
            messageResponseDTO.setResponse("Password already in use");
            return messageResponseDTO;
        } else {
            employee.setPassword(passwordDTO.getNewPassword());
            employeeRepository.save(employee);
            messageResponseDTO.setResponse("Password updated successfully");
            return messageResponseDTO;
        }

    }
}
