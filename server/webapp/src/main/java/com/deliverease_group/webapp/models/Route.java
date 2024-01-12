package com.deliverease_group.webapp.models;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "distribution_centre_id")
    private DistributionCentre distributionCentre;

    @ElementCollection
    @CollectionTable
    private List<Long> orderId; // not intended to be directly connected to the order ids in db.

    private Long driverId;

    private ZonedDateTime date;

    private boolean isComplete;


    public Route() {
    }

    public Route(DistributionCentre distributionCentre,
                 ArrayList<Long> orderId,
                 Long driverId,
                 ZonedDateTime date,
                 boolean isComplete) {
        this.distributionCentre = distributionCentre;
        this.orderId = orderId;
        this.driverId = driverId;
        this.date = date;
        this.isComplete = isComplete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DistributionCentre getDistributionCentre() {
        return distributionCentre;
    }

    public void setDistributionCentre(DistributionCentre distributionCentre) {
        this.distributionCentre = distributionCentre;
    }


    public List<Long> getOrderId() {
        return orderId;
    }

    public void setOrderId(List<Long> orderId) {
        this.orderId = orderId;
    }

    public void setOrderId(ArrayList<Long> orderId) {
        this.orderId = orderId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}