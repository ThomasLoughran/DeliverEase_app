package com.deliverease_group.webapp.dtos;

import com.deliverease_group.webapp.models.DistributionCentre;
import com.deliverease_group.webapp.models.DistributionCentreLocation;
import com.deliverease_group.webapp.models.Driver;
import com.deliverease_group.webapp.models.Role;

import java.util.HashMap;

public class DriverDTO {

    private String name;

    private String password;

    private Long distributionCentreId;

    private int vanCapacity;

    private int vanMaxWeight;

    private String vanName;

    public DriverDTO() {
    }

    public DriverDTO(String name, String password, Long distributionCentreId, int vanCapacity, int vanMaxWeight, String vanName) {
        this.name = name;
        this.password = password;
        this.distributionCentreId = distributionCentreId;
        this.vanCapacity = vanCapacity;
        this.vanMaxWeight = vanMaxWeight;
        this.vanName = vanName;
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

    public Long getDistributionCentreId() {
        return distributionCentreId;
    }

    public void setDistributionCentreId(Long distributionCentreId) {
        this.distributionCentreId = distributionCentreId;
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
}
