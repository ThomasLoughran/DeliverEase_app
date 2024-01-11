package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
