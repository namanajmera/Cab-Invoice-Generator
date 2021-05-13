package com.cabinvoicegenerator;

public class Ride {
    public double distance;
    public int time;
    public CabRide rideType;

    public Ride(double distance, int time, CabRide rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
    }
}
