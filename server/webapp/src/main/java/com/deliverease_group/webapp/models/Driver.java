package com.deliverease_group.webapp.models;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Driver extends Employee{


    private int vanCapacity; // volume m3
    private int vanMaxWeight; // kg

    private String vanName;

    private Boolean capacityFull;

    @ElementCollection
    @CollectionTable
    private List<ZonedDateTime> unavailableDates;


    public Driver(String name,
                  String password,
                  Role role,
                  DistributionCentre distributionCentre,
                  int vanCapacity,
                  int vanMaxWeight,
                  String vanName,
                  Boolean capacityFull,
                  ArrayList<ZonedDateTime> unavailableDates) {
        super(name, password, role, distributionCentre);
        this.vanCapacity = vanCapacity;
        this.vanMaxWeight = vanMaxWeight;
        this.vanName = vanName;
        this.capacityFull = capacityFull;
        this.unavailableDates = unavailableDates;
    }

    public Driver() {
    }

    public int getVanCapacity() {
        return vanCapacity;
    }

    public void setVanCapacity(int vanCapacity) {
        this.vanCapacity = vanCapacity;
    }

    public int getVanMaxWeight() {
        return vanMaxWeight;
    }

    public void setVanMaxWeight(int vanMaxWeight) {
        this.vanMaxWeight = vanMaxWeight;
    }

    public String getVanName() {
        return vanName;
    }

    public void setVanName(String vanName) {
        this.vanName = vanName;
    }

    public Boolean getCapacityFull() {
        return capacityFull;
    }

    public void setCapacityFull(Boolean capacityFull) {
        this.capacityFull = capacityFull;
    }

    public List<ZonedDateTime> getUnavailableDates() {
        return unavailableDates;
    }

    public void setUnavailableDates(List<ZonedDateTime> unavailableDates) {
        this.unavailableDates = unavailableDates;
    }

    public void setUnavailableDates(ArrayList<ZonedDateTime> unavailableDates) {
        this.unavailableDates = unavailableDates;
    }
}
