package com.cabinvoicegenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository {
    private Map<Integer, List<Ride>> userRidesRepository;

    public RideRepository() {
        this.userRidesRepository = new HashMap<Integer, List<Ride>>();
    }

    public void addRide(int userId, List<Ride> rides) {
        List<Ride> userRideList = this.userRidesRepository.get(userId);
        if(userRideList == null) userRideList =new ArrayList<>(rides);
        else userRideList.addAll(rides);
        this.userRidesRepository.put(userId, userRideList);
    }

    public List<Ride> getUserRideList(int userId){
        return this.userRidesRepository.get(userId);
    }

    public Map<Integer, List<Ride>> getRideRepositoryMap(){
        return this.userRidesRepository;
    }
}