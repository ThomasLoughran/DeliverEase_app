package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public interface RouteRepository extends JpaRepository<Route,Long> {

    List<Route> findAllByDistributionCentreIdAndDate(Long distributionCentreId, ZonedDateTime date);

    List<Route> findAllRoutesByDistributionCentreIdAndIsComplete(Long distCentreId, boolean isComplete);

    Route findRouteByDriverIdAndDate(Long driverId, ZonedDateTime localDate);
}
