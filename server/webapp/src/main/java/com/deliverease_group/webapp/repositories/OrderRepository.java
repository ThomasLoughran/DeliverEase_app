package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
