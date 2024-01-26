package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Role;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DistributionService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DistributionCentreRepository distributionCentreRepository;
    public List<Long> findAllDriverIdsByDistCentId(Long distCentId, Role role) {
        List<Employee> allDrivers = employeeRepository.findAllByDistributionCentreIdAndRole(distCentId, role);
        List<Long> allDriverIds = new ArrayList<>();
        for (Employee employee : allDrivers) {
           allDriverIds.add(employee.getId());
        }
        return allDriverIds;
    }


    public List<DistributionCentre> findAllDistributionCentres() {
        List<DistributionCentre> allDistributionCentres = distributionCentreRepository.findAll();
        return allDistributionCentres;
    }

}
