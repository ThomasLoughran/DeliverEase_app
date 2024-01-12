package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Role;
import com.deliverease_group.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DriverService {

    @Autowired
    EmployeeRepository employeeRepository;
    public List<Employee> findAllDriverIdsByDistCentId(Long distCentId, Role role) {

        return employeeRepository.findAllByDistributionCentreIdAndRole(distCentId, role);
    }


}
