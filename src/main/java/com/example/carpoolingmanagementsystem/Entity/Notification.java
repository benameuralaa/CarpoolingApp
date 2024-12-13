package com.example.carpoolingmanagementsystem.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    private String message; // Le message de la notification
    private Timestamp sentAt; // Date d'envoi de la notification
    private Status status; // Statut de la notification (envoyée ou lue)

    // Enumération pour le statut de la notification
    public enum Status {
        SENT, READ
    }

    // Relation avec l'entité User
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Relation avec la table 'users'
    private User user;

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
