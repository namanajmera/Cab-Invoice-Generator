package com.cabinvoicegenerator;

public class CabInvoiceGenerator {

    private static final double COST_PER_KILOMETER = 10.0;
    private static final int COST_PER_MINUTE = 1;
    private static final double MINIMUM_FARE = 5;

    public double calculateFare(double distance, int time) {
        if (distance<MINIMUM_FARE)
            return MINIMUM_FARE;
        return distance * COST_PER_KILOMETER +time * COST_PER_MINUTE;
    }

    public double calculateFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride:rides) {
            totalFare+=calculateFare(ride.distance,ride.time);
        }
        return totalFare;
    }
}
