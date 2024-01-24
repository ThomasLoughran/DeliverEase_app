package com.deliverease_group.webapp.models;

public enum DistributionCentreLocation {
    BIRMINGHAM(-1.898575, 52.489471, "(0121) 858 7828"),
    BRISTOL(-2.587910, 51.454514, "(0117) 013 5266"),
    LONDON(-0.118092, 51.509865, "(020) 6786 4026"),
    MANCHESTER(-2.244644, 53.483959, "(0161) 822 0308"),
    OXFORD(-1.257677, 51.752022, "(01235) 116678"),
    CARDIFF(-3.179090, 51.481583, "(029) 0835 7018");

    private final double X;

    private final double Y;

    private final String phoneNumber;

    DistributionCentreLocation(double X, double Y, String phoneNumber) {
        this.X = X;
        this.Y = Y;
        this.phoneNumber = phoneNumber;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

