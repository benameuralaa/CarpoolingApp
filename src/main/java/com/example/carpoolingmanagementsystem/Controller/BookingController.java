package com.example.carpoolingmanagementsystem.Controller;

import com.example.carpoolingmanagementsystem.Entity.Booking;
import com.example.carpoolingmanagementsystem.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/createBooking")
    public Booking createBooking(@RequestParam int rideId, @RequestParam int passengerId) {
        return bookingService.createBooking(rideId, passengerId);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @PutMapping("/cancel/{id}")
    public Booking cancelBooking(@PathVariable int id) {
        return bookingService.cancelBooking(id);
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable int id) {
        return bookingService.getBookingById(id);
    }
}