package com.deliverease_group.webapp.services;

import com.deliverease_group.webapp.dtos.DriverDTO;
import com.deliverease_group.webapp.models.*;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.DriverRepository;
import com.deliverease_group.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    DistributionCentreRepository distributionCentreRepository;

    public List<Employee> findAllDriverIdsByDistCentId(Long distCentId, Role role) {

        return employeeRepository.findAllByDistributionCentreIdAndRole(distCentId, role);
    }

    public List<Driver> findAllAvailableDrivers(Long distCentId, LocalDate date) {
        return driverRepository.availableDrivers(distCentId, date);
    }

    public Driver getDriverById(Long driverId){
        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isPresent()){
            return optionalDriver.get();
        }
        return null;
    }

    public List<Driver> getAllDrivers(){
        return driverRepository.findAll();
    }

    public Driver updateDriverAvailability(Long driverId, LocalDate date) {

        Optional<Driver> optionalDriver = driverRepository.findById(driverId);
        if (optionalDriver.isPresent()){
            Driver driver = optionalDriver.get();
            driver.updateAvailableDates(date);
            driverRepository.save(driver);
            return driver;
        } else {
            return null;
        }
    }

    public Driver addNewDriver(DriverDTO driverDTO) {
        DistributionCentre distributionCentre = distributionCentreRepository.findById(driverDTO.getDistributionCentreId()).get();
        Driver driver = new Driver(driverDTO.getName(), driverDTO.getPassword(), Role.DRIVER, distributionCentre, driverDTO.getVanCapacity(), driverDTO.getVanMaxWeight(), driverDTO.getVanName(), false, new HashMap<>());
        driverRepository.save(driver);
        return driver;
    }
}
