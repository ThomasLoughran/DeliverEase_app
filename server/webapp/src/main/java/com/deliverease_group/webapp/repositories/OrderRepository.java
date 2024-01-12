package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByDistributionCentreId(Long distCentreId);

}
