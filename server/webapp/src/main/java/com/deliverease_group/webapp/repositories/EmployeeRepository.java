package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}