package com.deliverease_group.webapp.repositories;

import com.deliverease_group.webapp.models.DistributionCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistributionCentreRepository extends JpaRepository<DistributionCentre,Long> {
}
