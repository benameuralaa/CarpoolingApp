package com.example.carpoolingmanagementsystem.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relation avec l'entité Rides
    @ManyToOne
    @JoinColumn(name = "ride_id", nullable = false) // Relation avec la table 'rides'
    private Ride ride;

    // Relation avec l'entité User (passenger)
    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false) // Relation avec la table 'users'
    private User passenger;

    private int seatsReserved;
    private Timestamp reservationDate;

    @Enumerated(EnumType.STRING)
    private Status status;  // Statut de la réservation

    // Enumération pour le statut de la réservation
    public enum Status {
        CONFIRMED, CANCELLED
    }
    //test par salma mh

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public User getPassenger() {
        return passenger;
    }

    public void setPassenger(User passenger) {
        this.passenger = passenger;
    }

    public int getSeatsReserved() {
        return seatsReserved;
    }

    public void setSeatsReserved(int seatsReserved) {
        this.seatsReserved = seatsReserved;
    }

    public Timestamp getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Timestamp reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
