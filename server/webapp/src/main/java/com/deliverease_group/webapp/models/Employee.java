package com.deliverease_group.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    private String password;

    private Role role;

    @ManyToOne
    @JoinColumn(name = "distribution_centre_id")
    @JsonIgnoreProperties({ "employees" })
//    @JsonIgnore
    private DistributionCentre distributionCentre;


    public Employee(String name, String password, Role role, DistributionCentre distributionCentre) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.distributionCentre = distributionCentre;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public DistributionCentre getDistributionCentre() {
        return distributionCentre;
    }

    public void setDistributionCentre(DistributionCentre distributionCentre) {
        this.distributionCentre = distributionCentre;
    }


}