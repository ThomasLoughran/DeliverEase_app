package com.deliverease_group.webapp.components;

import com.deliverease_group.webapp.models.*;
import com.deliverease_group.webapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DistributionCentreRepository distributionCentreRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    DriverRepository driverRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args) throws Exception{

        DistributionCentre birminghamCentre = new DistributionCentre(DistributionCentreLocation.BIRMINGHAM);
        distributionCentreRepository.save(birminghamCentre);

        Manager manager1 = new Manager("Manager 1", "password", Role.MANAGER, birminghamCentre);
        employeeRepository.save(manager1);

        Driver driver1 = new Driver("Driver 1", "password", Role.DRIVER, birminghamCentre, 500000, 1000, "A caravan", false, new ArrayList<>());
        employeeRepository.save(driver1);

        Order order1 = new Order(
                birminghamCentre,
                12,
                15,
                "W3 6JJ",
                "33 Yoke Road",
                10.21,
                80.25,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order1);

        ArrayList<Long> orderList = new ArrayList<>();
        orderList.add(order1.getId());
        Route route1 = new Route(birminghamCentre, orderList, driver1.getId(), ZonedDateTime.now(), false
        );
        routeRepository.save(route1);

    }
}