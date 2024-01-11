package com.deliverease_group.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drivers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    private String password;

    private Role role;

    private int vanCapacity; // volume m3
    private int vanMaxWeight; // kg

    private String vanName;

    private Boolean capacityFull;


    @ManyToOne
    @JoinColumn(name = "distribution_centre_id")
    @JsonIgnoreProperties({"drivers"})
    @JsonIgnore
    private DistributionCentre distributionCentre;

    @ElementCollection
    @CollectionTable

    private List<ZonedDateTime> unavailableDates;

    public Driver(String name, String password, Role role, int vanCapacity, int vanMaxWeight, String vanName, Boolean capacityFull, DistributionCentre distributionCentre, List<ZonedDateTime> unavailableDates) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.vanCapacity = vanCapacity;
        this.vanMaxWeight = vanMaxWeight;
        this.vanName = vanName;
        this.capacityFull = capacityFull;
        this.distributionCentre = distributionCentre;
        this.unavailableDates = unavailableDates;
    }

    public Driver() {
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

    public DistributionCentre getDistributionCentre() {
        return distributionCentre;
    }

    public void setDistributionCentre(DistributionCentre distributionCentre) {
        this.distributionCentre = distributionCentre;
    }
}