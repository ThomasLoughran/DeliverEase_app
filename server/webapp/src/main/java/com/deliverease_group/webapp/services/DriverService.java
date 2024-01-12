package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Role;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class DriverService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DriverRepository driverRepository;

    public List<Employee> findAllDriverIdsByDistCentId(Long distCentId, Role role) {

        return employeeRepository.findAllByDistributionCentreIdAndRole(distCentId, role);
    }

    public List<Driver> findAllAvailableDrivers(Long distCentId, LocalDate date) {
        return driverRepository.availableDrivers(distCentId, date);
    }

}
