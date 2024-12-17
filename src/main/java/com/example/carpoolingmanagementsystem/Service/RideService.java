package com.example.carpoolingmanagementsystem.Service;

import com.example.carpoolingmanagementsystem.Entity.Ride;
import com.example.carpoolingmanagementsystem.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository; // Injection automatique du repository

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public List<Ride> searchRides(String departure, String destination, LocalDateTime departureTime, Integer passengers) {
        // Construct query logic based on the search parameters
        return rideRepository.findRides(departure, destination, departureTime, passengers);
    }
}