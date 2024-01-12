package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Role;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class DistributionService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DriverRepository driverRepository;
    public List<Long> findAllDriverIdsByDistCentId(Long distCentId, Role role) {

        List<Employee> allDrivers = employeeRepository.findAllByDistributionCentreIdAndRole(distCentId, role);
        List<Long> allDriverIds = new ArrayList<>();
        for (Employee employee : allDrivers) {
           allDriverIds.add(employee.getId());

        }
        return allDriverIds;
    }




}
