package com.cabinvoicegenerator;

public class EnhancedInvoice {
    private int numberOfRides;
    private double totalFare;
    private double averageFarePerRide;

    public EnhancedInvoice(int numberOfRides, double totalFare) {
        this.numberOfRides = numberOfRides;
        this.totalFare = totalFare;
        this.averageFarePerRide = (numberOfRides != 0) ? (totalFare / numberOfRides) : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EnhancedInvoice other = (EnhancedInvoice) obj;
        if (Double.doubleToLongBits(averageFarePerRide) != Double.doubleToLongBits(other.averageFarePerRide))
            return false;
        if (numberOfRides != other.numberOfRides)
            return false;
        if (Double.doubleToLongBits(totalFare) != Double.doubleToLongBits(other.totalFare))
            return false;
        return true;
    }
}
