package com.deliverease_group.webapp.models;

public class Node {

    private double x;

    private double y;

    private double radius;

    private double theta;

    private long orderId;

    public Node(double x, double y, long id) {
        this.x = x;
        this.y = y;
        this.orderId = id;

    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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
