package com.example.carpoolingmanagementsystem.Service;

import com.example.carpoolingmanagementsystem.Entity.Booking;
import com.example.carpoolingmanagementsystem.Entity.Ride;
import com.example.carpoolingmanagementsystem.Entity.User;
import com.example.carpoolingmanagementsystem.Repository.BookingRepository;
import com.example.carpoolingmanagementsystem.Repository.RideRepository;
import com.example.carpoolingmanagementsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    public Booking createBooking(int rideId, int passengerId) {
        // nchoufou si ride w passenger existe ou non
        Ride ride = rideRepository.findById(rideId).orElse(null);
        User passenger = userRepository.findById(passengerId).orElse(null);

        if (ride == null) {
            throw new IllegalArgumentException("Ride not found");
        }
        if (passenger == null) {
            throw new IllegalArgumentException("User not found");
        }

        // nchoufou s'il y a suffisamment de places disponibles
        int availableSeats = ride.getSeatsAvailable();
        if (availableSeats <= 0) {
            throw new IllegalArgumentException("No seats available");
        }
        int seatsReserved = 1;

        // tawa na3mlou creation mte3 réservation
        Booking booking = new Booking();
        booking.setRide(ride);
        booking.setPassenger(passenger);
        booking.setSeatsReserved(seatsReserved);
        booking.setReservationDate(Timestamp.valueOf(LocalDateTime.now()));
        booking.setStatus(Booking.Status.CONFIRMED); // par defaut il status CONFIRMED

        // Decrease the number of available seats in the ride
        ride.setSeatsAvailable(ride.getSeatsAvailable() - seatsReserved);
        rideRepository.save(ride);

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking cancelBooking(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }

        // Increase the number of available seats in the ride
        Ride ride = booking.getRide();
        ride.setSeatsAvailable(ride.getSeatsAvailable() + booking.getSeatsReserved());
        rideRepository.save(ride);

        booking.setStatus(Booking.Status.CANCELLED);
        return bookingRepository.save(booking);
    }

    public Booking getBookingById(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        return booking;
    }
}
