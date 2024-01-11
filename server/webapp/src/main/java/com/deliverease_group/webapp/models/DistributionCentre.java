package com.deliverease_group.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "distribution_centre")
public class DistributionCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private DistributionCentreLocation location;

    @OneToMany(mappedBy = "distributionCentre")
    private ArrayList<Order> orders;

    @OneToMany(mappedBy = "distributionCentre")
    private ArrayList<Route> routes;
    @JsonIgnoreProperties({"distribution_centre"})
    @OneToMany(mappedBy = "distributionCentre")
    private ArrayList<Employee> employees;

    public DistributionCentre() {
    }

    public DistributionCentre(DistributionCentreLocation location) {
        this.location = location;
        this.orders = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DistributionCentreLocation getLocation() {
        return location;
    }

    public void setLocation(DistributionCentreLocation location) {
        this.location = location;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }
}
