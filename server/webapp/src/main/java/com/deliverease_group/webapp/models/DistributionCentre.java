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

    @Column
    private double latitude;

    @Column
    private double longitude;

    @OneToMany(mappedBy = "distributionCentre")
    @Column
    @JsonIgnoreProperties({ "distributionCentre" })
    private List<Order> orders;

    @OneToMany(mappedBy = "distributionCentre")
    @Column
    @JsonIgnoreProperties({ "distributionCentre" })
    private List<Route> routes;
    @JsonIgnoreProperties({"distribution_centre"})
    @OneToMany(mappedBy = "distributionCentre")
    @Column
    private List<Employee> employees;

    private String phoneNumber;

    public DistributionCentre() {
    }

    public DistributionCentre(DistributionCentreLocation location) {
        this.location = location;
        this.orders = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.phoneNumber = location.getPhoneNumber();
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
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

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}