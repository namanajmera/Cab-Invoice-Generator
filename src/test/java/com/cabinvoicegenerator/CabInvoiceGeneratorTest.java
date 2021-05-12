package com.cabinvoicegenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CabInvoiceGeneratorTest {
    @Test
    public void givenDistanceAndTime_ShouldReturnFare() {
        CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
        double distance=10.0;
        int time=5;
        double fare=invoiceGenerator.calculateFare(distance,time);
        Assertions.assertEquals(105,fare,0.0);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnMinimumFare() {
        CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
        double distance=0.1;
        int time=1;
        double fare=invoiceGenerator.calculateFare(distance,time);
        Assertions.assertEquals(5,fare,0.0);
    }
}