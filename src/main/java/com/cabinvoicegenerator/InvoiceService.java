package com.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.List;

public class InvoiceService {
    private List<RideRepository> repository;

    public InvoiceService() {
        repository = new ArrayList<RideRepository>();
    }

    public InvoiceService(List<RideRepository> repository) {
        this.repository = repository;
    }

    public EnhancedInvoice getInvoice(int userId) {
        EnhancedInvoice invoiceSummary = null;
        for (RideRepository userRides : repository) {
            if (userRides.userId == userId) {
                invoiceSummary = new CabInvoiceGenerator().getInvoiceSummary(userRides.rides);
            }
        }
        return invoiceSummary;
    }
}
