package com.deliverease_group.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "distribution_centre")
public class DistributionCentre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private DistributionCentreLocation location;

    @OneToMany(mappedBy = "distributionCentre")
    @Column
    private List<Order> orders;

    @OneToMany(mappedBy = "distributionCentre")
    @Column
    private List<Route> routes;
    @JsonIgnoreProperties({"distribution_centre"})
    @OneToMany(mappedBy = "distributionCentre")
    @Column
    private List<Manager> managers;

    public DistributionCentre() {
    }

    public DistributionCentre(DistributionCentreLocation location) {
        this.location = location;
        this.orders = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.managers = new ArrayList<>();
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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }
}
