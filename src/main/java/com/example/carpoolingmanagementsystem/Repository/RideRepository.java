package com.example.carpoolingmanagementsystem.Repository;

import com.example.carpoolingmanagementsystem.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RideRepository extends JpaRepository<Ride, Integer> {
}

