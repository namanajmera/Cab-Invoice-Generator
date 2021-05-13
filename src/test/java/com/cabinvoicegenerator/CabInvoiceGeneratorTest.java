package com.cabinvoicegenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CabInvoiceGeneratorTest {
    CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();

    @Test
    public void givenDistanceAndTime_ShouldReturnFare() {
        double distance = 10.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(105, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assertions.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnTotalFare() {
        Ride[] rides = {
                new Ride(10.0, 5),
                new Ride(0.1, 1)
        };
        double totalFare = invoiceGenerator.calculateFare(rides);
        Assertions.assertEquals(110, totalFare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        Ride[] rides = {
                new Ride(5.0, 10),
                new Ride(0.3, 1),
                new Ride(1.0, 5)
        };
        EnhancedInvoice invoiceSummary = invoiceGenerator.getInvoiceSummary(rides);
        EnhancedInvoice expectedInvoiceSummary = new EnhancedInvoice(3, 80.0);
        Assertions.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenUserId_ShouldReturnInvoiceSummary() {
        RideRepository[] repositoryList = {new RideRepository(101, new Ride[]{new Ride(5.0, 10), new Ride(0.3, 1), new Ride(1.0, 5)}),
                new RideRepository(102, new Ride[]{new Ride(5.5, 10), new Ride(0.2, 2), new Ride(3.0, 7)}),
                new RideRepository(103, new Ride[]{new Ride(6.0, 10), new Ride(0.1, 3), new Ride(5.0, 10)})
        };
        InvoiceService invoiceService = new InvoiceService(Arrays.asList(repositoryList));
        EnhancedInvoice invoiceSummary = invoiceService.getInvoice(101);
        EnhancedInvoice expectedInvoiceSummary = new EnhancedInvoice(3, 80.0);
        Assertions.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }
}
