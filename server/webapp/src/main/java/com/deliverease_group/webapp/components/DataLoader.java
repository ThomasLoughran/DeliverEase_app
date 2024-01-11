package com.deliverease_group.webapp.components;

import com.deliverease_group.webapp.models.*;
import com.deliverease_group.webapp.repositories.DistributionCentreRepository;
import com.deliverease_group.webapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DistributionCentreRepository distributionCentreRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args) throws Exception{

        DistributionCentre birminghamCentre = new DistributionCentre(DistributionCentreLocation.BIRMINGHAM);
        distributionCentreRepository.save(birminghamCentre);

       Manager manager1 = new Manager("Manager 1", "password", Role.MANAGER, birminghamCentre);
       employeeRepository.save(manager1);


    }
}
