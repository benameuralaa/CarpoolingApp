package com.example.carpoolingmanagementsystem.Controller;

import com.example.carpoolingmanagementsystem.Entity.Ride;
import com.example.carpoolingmanagementsystem.Service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rides")
public class RideController {
    @Autowired
    private RideService rideService;

    @PostMapping
    public Ride createRide(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @GetMapping
    public List<Ride> getAllRides() {
        return rideService.getAllRides();
    }

    @GetMapping("/{id}")
    public Ride getRideById(@PathVariable int id) {
        return rideService.getRideById(id); // getRideById(id) traja3 exception ken mouch mawjouda
    }

    @PutMapping("/{id}")
    public Ride updateRide(@PathVariable int id, @RequestBody Ride rideDetails) {
        return rideService.updateRide(id, rideDetails); // fostha il getRideById donc traja3 exception zeda ken mouch mawjouda
    }

    @DeleteMapping("/{id}")
    public String deleteRide(@PathVariable int id) {
        rideService.deleteRide(id);
        return "Ride with ID " + id + " has been deleted successfully.";
    }
}
