package com.cabinvoicegenerator;

public class CabInvoiceGenerator {

    private static final double COST_PER_KILOMETER = 10.0;
    private static final int COST_PER_MINUTE = 1;
    private static final double MINIMUM_RIDE_FARE = 5.0;

    public double calculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return Math.max(MINIMUM_RIDE_FARE, totalFare);
    }

    public double calculateFare(Ride[] rides) {
        double totalFareForAllRides = 0.0;
        for (Ride ride : rides)
            totalFareForAllRides += calculateFare(ride.distance, ride.time);
        return totalFareForAllRides;
    }

    public EnhancedInvoice getInvoiceSummary(Ride[] rides) {
        double totalFare = calculateFare(rides);
        return new EnhancedInvoice(rides.length, totalFare);
    }
}
