package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Manager;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    ManagerRepository managerRepository;

    public List<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

    public Manager getManagerById(Long managerId){
        Optional<Manager> optionalManager = managerRepository.findById(managerId);
        if (optionalManager.isPresent()){
            return optionalManager.get();
        }
        return null;
    }

    public List<Manager> getAllManagers(){
        return managerRepository.findAll();
    }



}