package com.deliverease_group.webapp.models;

public class Node {

    private double longitude;

    private double latitude;

    private double radius;

    private double theta;

    private long orderId;

    public Node(double longitude, double latitude, long id) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.orderId = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
