package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.Driver;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface DriverRepository extends JpaRepository<Driver,Long> {

    @Query(value = "SELECT * FROM drivers\n" +
            "INNER JOIN driver_available_dates \n" +
            "ON drivers.id = driver_available_dates.driver_id\n" +
            "INNER JOIN employees\n" +
            "ON driver_available_dates.driver_id = employees.id\n" +
            "WHERE employees.distribution_centre_id = :param1\n" +
            "AND driver_available_dates.available_dates::text\n" +
            "LIKE :param2", nativeQuery = true)
    List<Driver> availableDrivers(@Param("param1") Long distCentId, @Param("param2") String selectedDate);
}
