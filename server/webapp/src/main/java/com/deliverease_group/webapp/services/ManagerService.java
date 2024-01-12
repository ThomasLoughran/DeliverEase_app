package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    @Autowired
    DriverRepository driverRepository;

    public List<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

}