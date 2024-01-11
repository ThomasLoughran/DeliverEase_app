package com.deliverease_group.webapp.models;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "distribution_centre_id")
    private DistributionCentre distributionCentre;


    private int size;
    private int weight;
    private String postcode;
    private String address;
    private double latitude;
    private double longitude;
    private ZonedDateTime dateOrdered;
    private boolean isCompleted;
    private boolean isManagerReviewed;
    private boolean isFragile;
    private Issue issue;
    private ZonedDateTime timeIssuePosted;

    public Order() {

    }

    public Order(Long id, String name) {
        this.name = name;
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

    public DistributionCentre getDistributionCentre() {
        return distributionCentre;
    }

    public void setDistributionCentre(DistributionCentre distributionCentre) {
        this.distributionCentre = distributionCentre;
    }
}
