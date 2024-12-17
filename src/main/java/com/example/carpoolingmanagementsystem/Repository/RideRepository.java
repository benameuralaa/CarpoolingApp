package com.example.carpoolingmanagementsystem.Repository;

import com.example.carpoolingmanagementsystem.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface RideRepository extends JpaRepository<Ride, Long> {
    @Query("SELECT r FROM Ride r WHERE "
            + "(r.startLocation LIKE %:departure% OR :departure IS NULL) "
            + "AND (r.endLocation LIKE %:destination% OR :destination IS NULL) "
            + "AND (:departureTime IS NULL OR r.departureTime >= :departureTime) "
            + "AND (:passengers IS NULL OR r.seatsAvailable = :passengers)")
    List<Ride> findRides(@Param("departure") String departure,
                         @Param("destination") String destination,
                         @Param("departureTime") LocalDateTime departureTime,
                         @Param("passengers") Integer passengers);
}

