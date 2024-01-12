package com.deliverease_group.webapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "managers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Manager extends Employee {


    public Manager(String name, String password, Role role, DistributionCentre distributionCentre) {
        super(name, password, role, distributionCentre);
    }

    public Manager() {
    }
}