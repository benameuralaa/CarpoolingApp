package com.example.carpoolingmanagementsystem.Service;
import com.example.carpoolingmanagementsystem.Entity.Ride;
import com.example.carpoolingmanagementsystem.Repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(int id) {
        Ride ride = rideRepository.findById(id).orElse(null);
        if (ride == null) {
            throw new RuntimeException("Ride not found with id " + id);
        }
        return ride;
    }

    public Ride createRide(Ride ride) {
        return rideRepository.save(ride);
    }

    public Ride updateRide(int id, Ride rideDetails) {
        Ride ride = getRideById(id);
        ride.setStartLocation(rideDetails.getStartLocation());
        ride.setEndLocation(rideDetails.getEndLocation());
        ride.setDepartureTime(rideDetails.getDepartureTime());
        ride.setSeatsAvailable(rideDetails.getSeatsAvailable());
        ride.setPricePerSeat(rideDetails.getPricePerSeat());
        ride.setRestrictions(rideDetails.getRestrictions());
        ride.setDriver(rideDetails.getDriver());
        return rideRepository.save(ride);
    }

    public void deleteRide(int id) {
        Ride ride = getRideById(id);
        rideRepository.delete(ride);
    }
}
