package com.deliverease_group.webapp.components;

import com.deliverease_group.webapp.models.*;
import com.deliverease_group.webapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

        DistributionCentre bristolCentre = new DistributionCentre(DistributionCentreLocation.BRISTOL);
        distributionCentreRepository.save(bristolCentre);

        DistributionCentre londonCentre = new DistributionCentre(DistributionCentreLocation.LONDON);
        distributionCentreRepository.save(londonCentre);

        DistributionCentre manchesterCentre = new DistributionCentre(DistributionCentreLocation.MANCHESTER);
        distributionCentreRepository.save(manchesterCentre);

        DistributionCentre oxfordCentre = new DistributionCentre(DistributionCentreLocation.OXFORD);
        distributionCentreRepository.save(oxfordCentre);

        DistributionCentre cardiffCentre = new DistributionCentre(DistributionCentreLocation.CARDIFF);
        distributionCentreRepository.save(cardiffCentre);


        Manager manager1 = new Manager("Manager 1", "password", Role.MANAGER, birminghamCentre);
        employeeRepository.save(manager1);

        Manager manager2 = new Manager("Manager 2", "password", Role.MANAGER, londonCentre);
        employeeRepository.save(manager2);

        Manager manager3 = new Manager("Manager 3", "password", Role.MANAGER, bristolCentre);
        employeeRepository.save(manager3);

        Manager manager4 = new Manager("Manager 4", "password", Role.MANAGER, manchesterCentre);
        employeeRepository.save(manager4);

        Manager manager5 = new Manager("Manager 5", "password", Role.MANAGER, oxfordCentre);
        employeeRepository.save(manager5);

        Manager manager6 = new Manager("Manager 6", "password", Role.MANAGER, cardiffCentre);
        employeeRepository.save(manager6);


        Driver driver1 = new Driver("Driver 1", "password", Role.DRIVER, birminghamCentre, 50, 1000, "A caravan", false, new HashMap<>());
        employeeRepository.save(driver1);

        Driver driver2 = new Driver("Driver 2", "password", Role.DRIVER, birminghamCentre, 100, 1000, "A bus", false, new HashMap<>());
        employeeRepository.save(driver2);

        Map<LocalDate, Boolean> availableDates =  new HashMap<>();
        availableDates.put(LocalDate.now(), true);

        Driver driver3 = new Driver("Driver 3", "password", Role.DRIVER, birminghamCentre, 100, 1000, "A bus", false, availableDates);
        employeeRepository.save(driver3);

        Driver driver4 = new Driver("Driver 4", "password", Role.DRIVER, birminghamCentre, 100, 1000, "A bus", false, availableDates);
        employeeRepository.save(driver4);

        //3 orders:
        Order order1 = new Order(
                birminghamCentre,
                12,
                15,
                "B12 0RL",
                "61 Angelina Street",
                52.467677,
                -1.890373,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order1);

        Order order2 = new Order(
                birminghamCentre,
                25,
                30,
                "B5 7LX",
                "4 Bellevue",
                52.465191,
                -1.900440,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order2);

        Order order3 = new Order(
                birminghamCentre,
                25,
                30,
                "B12 0HE",
                "6 New Moseley Road",
                52.468423,
                -1.880994,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order3);



//       to generate data loader route:
//        ArrayList<Long> orderList = new ArrayList<>();
//        orderList.add(order1.getId());
//        orderList.add(order2.getId());
//        orderList.add(order3.getId());
//        Route route1 = new Route(birminghamCentre, orderList, driver1.getId(), ZonedDateTime.now(), false);
//        routeRepository.save(route1);

    }
}