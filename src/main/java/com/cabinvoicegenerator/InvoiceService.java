package com.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.List;

public class InvoiceService {
    public RideRepository userRideRepository;

    public InvoiceService() {
        this.userRideRepository = new RideRepository();
    }

    public EnhancedInvoice getInvoice(int userId) throws InvoiceException {
        if(!userRideRepository.getRideRepositoryMap().containsKey(userId))
            throw new InvoiceException("Invalid user id", InvoiceException.ExceptionType.NO_SUCH_USER);
        else {
            EnhancedInvoice invoiceSummary = getInvoiceSummary(userRideRepository.getUserRideList(userId));
            return invoiceSummary;
        }
    }

    public EnhancedInvoice getInvoiceSummary(List<Ride> rides) throws InvoiceException {
        double totalFare = new CabInvoiceGenerator().calculateFare(rides);
        return new EnhancedInvoice(rides.size(), totalFare);
    }

    public void addRide(int userId, List<Ride> rides) {
        userRideRepository.addRide(userId, rides);
    }

    public int countUserRides(int userId) {
        return userRideRepository.getUserRideList(userId).size();
    }
}
