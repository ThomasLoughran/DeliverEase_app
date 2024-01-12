package com.deliverease_group.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "distribution_centre_id")
    @JsonIgnore
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

    public Order(DistributionCentre distributionCentre, int size, int weight, String postcode, String address, double latitude, double longitude, ZonedDateTime dateOrdered, boolean isCompleted, boolean isManagerReviewed, boolean isFragile, ZonedDateTime timeIssuePosted) {
        this.distributionCentre = distributionCentre;
        this.size = size;
        this.weight = weight;
        this.postcode = postcode;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dateOrdered = dateOrdered;
        this.isCompleted = isCompleted;
        this.isManagerReviewed = isManagerReviewed;
        this.isFragile = isFragile;
        this.issue = null;
        this.timeIssuePosted = timeIssuePosted;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public ZonedDateTime getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(ZonedDateTime dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public boolean isManagerReviewed() {
        return isManagerReviewed;
    }

    public void setManagerReviewed(boolean managerReviewed) {
        isManagerReviewed = managerReviewed;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public void setFragile(boolean fragile) {
        isFragile = fragile;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public ZonedDateTime getTimeIssuePosted() {
        return timeIssuePosted;
    }

    public void setTimeIssuePosted(ZonedDateTime timeIssuePosted) {
        this.timeIssuePosted = timeIssuePosted;
    }
}