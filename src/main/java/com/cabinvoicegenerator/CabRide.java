package com.cabinvoicegenerator;

public enum CabRide {
    NORMAL( 10.0, 1, 5.0),
    PREMIUM( 15.0, 2, 20.0);

    private final double costPerKilometer;
    private final int costPerMinute;
    private final double minimumCostPerRide;

    CabRide(double costPerKilometer, int costPerMinute, double minimumCostPerRide) {
        this.costPerKilometer = costPerKilometer;
        this.costPerMinute = costPerMinute;
        this.minimumCostPerRide = minimumCostPerRide;
    }

    public double calculateFarePerRide(Ride ride) {
        double totalFare = ride.distance * costPerKilometer + ride.time * costPerMinute;
        return Math.max(minimumCostPerRide, totalFare);
    }

    double calculateFarePerRide(double distance, int time) {
        double totalFare = distance * costPerKilometer + time * costPerMinute;
        return Math.max(minimumCostPerRide, totalFare);
    }
}