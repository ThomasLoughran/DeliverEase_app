package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Order;
import com.deliverease_group.webapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByDistributionCentreId(Long distCentreId);

    List<Order> findAllByDistributionCentreIdAndIsCompletedOrderByDateOrdered(Long distCentreId, boolean b);
    //List<Employee> findAllByDistributionCentreIdAndRole(Long distCentId, Role role);
}
