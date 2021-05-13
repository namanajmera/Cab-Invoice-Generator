package com.cabinvoicegenerator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CabInvoiceGeneratorTest {
    CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
    InvoiceService invoiceService = new InvoiceService();

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 5.0;
        int time = 10;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(60.0, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_WhenFareLessThanMinimumFare_ShouldReturnMinimumFare() {
        double distance = 0.3;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5.0, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {
                new Ride(5.0, 10, CabRide.NORMAL),
                new Ride(0.3, 1, CabRide.NORMAL),
                new Ride(1.0, 5, CabRide.NORMAL)
        };
        try {
            double totalFare = invoiceGenerator.calculateFare(Arrays.asList(rides));
            Assert.assertEquals(80.0, totalFare, 0.0);
        } catch (InvoiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(5.0, 10,  CabRide.NORMAL),
                new Ride(0.3, 1,  CabRide.NORMAL),
                new Ride(1.0, 5,  CabRide.NORMAL)
        };
        try {
            EnhancedInvoice invoiceSummary = invoiceService.getInvoiceSummary(Arrays.asList(rides));
            EnhancedInvoice expectedInvoiceSummary = new EnhancedInvoice(3, 80.0);
            Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
        } catch (InvoiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUserId_ShouldReturnInvoiceSummary() {
        int firstUserId = 101;
        Ride[] firstUserRides = {new Ride(5.0, 10, CabRide.NORMAL), new Ride(0.3, 1, CabRide.NORMAL), new Ride(1.0, 5, CabRide.NORMAL)};
        invoiceService.addRide(firstUserId, Arrays.asList(firstUserRides));

        int secondUserId = 102;
        Ride[] secondUserRides = { new Ride(5.5, 10, CabRide.NORMAL), new Ride(0.2, 2, CabRide.NORMAL), new Ride(3.0, 7, CabRide.NORMAL) };
        invoiceService.addRide(secondUserId, Arrays.asList(secondUserRides));

        int thirdUserId = 103;
        Ride[] thirdUserRides = { new Ride(6.0, 10, CabRide.NORMAL), new Ride(0.1, 3, CabRide.NORMAL), new Ride(5.0, 10, CabRide.NORMAL) };
        invoiceService.addRide(thirdUserId, Arrays.asList(thirdUserRides));
        try {
            EnhancedInvoice invoiceSummary = invoiceService.getInvoice(101);
            EnhancedInvoice expectedInvoiceSummary = new EnhancedInvoice(3, 80.0);
            Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
        }catch (InvoiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenDistanceAndTime_WhenPremiumRides_ShouldReturnTotalFare() {
        double distance = 5.0;
        int time = 10;
        double fare = invoiceGenerator.calculatePremiumRideFare(distance, time);
        Assert.assertEquals(95.0, fare, 0.0);
    }

    @Test
    public void givenDistanceAndTimeForPremiumRide_WhenFareLessThanMinimumFare_ShouldReturnMinimumFare() {
        double distance = 1.0;
        int time = 1;
        double fare = invoiceGenerator.calculatePremiumRideFare(distance, time);
        Assert.assertEquals(20.0, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_WhenRidesArePremium_ShouldReturnTotalPremiumRideFare() {
        Ride[] rides = {
                new Ride(5.0, 10, CabRide.PREMIUM),
                new Ride(1.0, 1, CabRide.PREMIUM),
                new Ride(1.0, 5, CabRide.PREMIUM)
        };
        try {
            double totalFare = invoiceGenerator.calculateFare(Arrays.asList(rides));
            Assert.assertEquals(140.0, totalFare, 0.0);
        } catch (InvoiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUserId_WhenBothNormalAndPremiumRidesArePresent_ShouldReturnInvoiceSummary() {
        int firstUserId = 101;
        Ride[] firstUserRides = { new Ride(5.0, 10, CabRide.NORMAL), new Ride(0.3, 1, CabRide.PREMIUM), new Ride(1.0, 5, CabRide.PREMIUM) };
        invoiceService.addRide(firstUserId, Arrays.asList(firstUserRides));

        int secondUserId = 102;
        Ride[] secondUserRides = { new Ride(5.5, 10, CabRide.PREMIUM), new Ride(0.2, 2, CabRide.PREMIUM), new Ride(3.0, 7, CabRide.NORMAL) };
        invoiceService.addRide(secondUserId, Arrays.asList(secondUserRides));

        int thirdUserId = 103;
        Ride[] thirdUserRides = { new Ride(6.0, 10, CabRide.PREMIUM), new Ride(0.1, 3, CabRide.PREMIUM), new Ride(5.0, 10, CabRide.PREMIUM) };
        invoiceService.addRide(thirdUserId, Arrays.asList(thirdUserRides));
        try {
            EnhancedInvoice invoiceSummary = invoiceService.getInvoice(101);
            EnhancedInvoice expectedInvoiceSummary = new EnhancedInvoice(3, 105.0);
            Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
        }catch (InvoiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void givenUserId_WhenRidesAddedToRepository_ShouldReturnUserRideListSize() {
        int userId = 101;
        Ride[] firstMonthRides = { new Ride(5.0, 10, CabRide.NORMAL), new Ride(0.3, 1, CabRide.PREMIUM), new Ride(1.0, 5, CabRide.PREMIUM) };
        invoiceService.addRide(userId, Arrays.asList(firstMonthRides));
        Ride[] secondMonthRides = { new Ride(6.0, 10, CabRide.NORMAL), new Ride(0.2, 1, CabRide.PREMIUM), new Ride(3.0, 5, CabRide.NORMAL) };
        invoiceService.addRide(userId, Arrays.asList(secondMonthRides));
        Assert.assertEquals(6, invoiceService.countUserRides(userId));
    }
}
