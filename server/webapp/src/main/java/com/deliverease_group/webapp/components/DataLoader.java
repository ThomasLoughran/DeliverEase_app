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

        Order order4 = new Order(
                birminghamCentre,
                25,
                30,
                "B14 7TG",
                "59 Avenue Road",
                52.433252,
                -1.900467,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order4);

        Order order5 = new Order(
                birminghamCentre,
                0,
                0,
                "B66 3TF",
                "5 Woodland Street",
                52.491102,
                -1.951745,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order5);

        Order order6 = new Order(
                birminghamCentre,
                0,
                0,
                "B3 1PU",
                "27 James Brindley Walk, Birmingham",
                52.482526,
                -1.909309,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order6);

        Order order7 = new Order(
                birminghamCentre,
                0,
                0,
                "B79 7BU",
                "34 Buckingham Road, Tamworth",
                52.644873,
                -1.727462,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order7);

        Order order8 = new Order(
                birminghamCentre,
                0,
                0,
                "B38 9FF",
                "17 Primrose Park Road, Birmingham",
                52.40083,
                -1.936396,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order8);

        Order order9 = new Order(
                birminghamCentre,
                0,
                0,
                "B90 4EZ",
                "17 Cheswick Way, Shirley",
                52.379199,
                -1.813562,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order9);

        Order order10 = new Order(
                birminghamCentre,
                0,
                0,
                "B34 7LD",
                "40 Berrowside Road, Birmingham",
                52.497569,
                -1.762391,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order10);

        Order order11 = new Order(
                birminghamCentre,
                0,
                0,
                "B61 9HS",
                "5 Dale Close, Bournheath",
                52.357733,
                -2.065488,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order11);

        Order order12 = new Order(
                birminghamCentre,
                0,
                0,
                "B75 5QZ",
                "Woodfields, Camp Road, Sutton Coldfield",
                52.601143,
                -1.803756,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order12);

        Order order13 = new Order(
                birminghamCentre,
                0,
                0,
                "B91 3FH",
                "68 Charterhouse Drive, Solihull",
                52.403107,
                -1.778832,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order13);

        Order order14 = new Order(
                birminghamCentre,
                0,
                0,
                "B50 4GA",
                "16 Chestnut Way, Bidford On Avon",
                52.171703,
                -1.855787,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order14);

        Order order15 = new Order(
                birminghamCentre,
                0,
                0,
                "B36 0TX",
                "158 Tamar Drive, Birmingham",
                52.50216,
                -1.752143,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order15);

        Order order16 = new Order(
                birminghamCentre,
                0,
                0,
                "B71 3TD",
                "22 Brynside Close, Birmingham",
                52.540829,
                -1.97088,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order16);

        Order order17 = new Order(
                birminghamCentre,
                0,
                0,
                "B74 3AR",
                "1 Beech Gate, Sutton Coldfield",
                52.598129,
                -1.866634,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order17);

        Order order18 = new Order(
                birminghamCentre,
                0,
                0,
                "B30 3AN",
                "19 Hudsons Drive, Birmingham",
                52.417356,
                -1.924978,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order18);

        Order order19 = new Order(
                birminghamCentre,
                0,
                0,
                "B24 9HX",
                "94 Orphanage Road, Birmingham",
                52.527353,
                -1.833034,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order19);

        Order order20 = new Order(
                birminghamCentre,
                0,
                0,
                "B90 4LA",
                "88 The Green Business Park, Solihull",
                52.395392,
                -1.816499,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order20);

        Order order21 = new Order(
                birminghamCentre,
                0,
                0,
                "B69 2JT",
                "157 New Birmingham Road, Tividale",
                52.510428,
                -2.047916,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order21);

        Order order22 = new Order(
                bristolCentre,
                0,
                0,
                "BS48 1LY",
                "8 Sawyers Close, Wraxall",
                51.435286,
                -2.743273,
                ZonedDateTime.now(),
                false,
                false,
                false,
                ZonedDateTime.now()
        );
        orderRepository.save(order22);



//       to generate data loader route:
//        ArrayList<Long> orderList = new ArrayList<>();
//        orderList.add(order1.getId());
//        orderList.add(order2.getId());
//        orderList.add(order3.getId());
//        Route route1 = new Route(birminghamCentre, orderList, driver1.getId(), ZonedDateTime.now(), false);
//        routeRepository.save(route1);

    }
}