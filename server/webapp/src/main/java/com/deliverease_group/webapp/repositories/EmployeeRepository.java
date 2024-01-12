package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.Employee;
import com.deliverease_group.webapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    // takes in dist cent id and employee role to begin finding driver availability:
    List<Employee> findAllByDistributionCentreIdAndRole(Long distCentId, Role role);
}