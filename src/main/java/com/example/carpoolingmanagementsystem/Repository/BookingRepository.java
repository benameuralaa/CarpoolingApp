package com.example.carpoolingmanagementsystem.Repository;

import com.example.carpoolingmanagementsystem.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}

