package com.deliverease_group.webapp.models;

public enum DistributionCentreLocation {
    BIRMINGHAM(-1.898575, 52.489471),
    BRISTOL(-2.587910, 51.454514),
    LONDON(-0.118092, 51.509865),
    MANCHESTER(-2.244644, 53.483959),
    OXFORD(-1.257677, 51.752022),
    CARDIFF(-3.179090, 51.481583);

    private final double X;
    private final double Y;

    DistributionCentreLocation(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }
}

