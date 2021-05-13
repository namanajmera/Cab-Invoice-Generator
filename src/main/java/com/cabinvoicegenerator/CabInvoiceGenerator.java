package com.cabinvoicegenerator;

import java.util.List;

public class CabInvoiceGenerator {
    public double calculateFare(double distance, int time) {
        return CabRide.NORMAL.calculateFarePerRide(distance, time);
    }

    public double calculatePremiumRideFare(double distance, int time) {
        return CabRide.PREMIUM.calculateFarePerRide(distance, time);
    }

    public double calculateFare(List<Ride> rides) throws InvoiceException{
        double totalFareForAllRides = 0.0;
        for (Ride ride : rides) {
            if(!(ride.rideType instanceof CabRide))
                throw new InvoiceException("Invalid ride category", InvoiceException.ExceptionType.INVALID_RIDE_TYPE);
            else
                totalFareForAllRides += ride.rideType.calculateFarePerRide(ride);
        }
        return totalFareForAllRides;
    }
}
