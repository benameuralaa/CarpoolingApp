package com.example.carpoolingmanagementsystem.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

    @Entity
    public class Review {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private double rating;
        @Lob
        private String comment;
        private Timestamp reviewDate;

        // Relation avec l'entité Rides
        @ManyToOne
        @JoinColumn(name = "ride_id", nullable = false) // Relation avec la table 'rides'
        private Ride ride;

        // Relation avec l'entité User (le reviewer)
        @ManyToOne
        @JoinColumn(name = "reviewer_id", nullable = false) // Relation avec la table 'users' (la personne qui laisse un avis)
        private User reviewer;

        // Relation avec l'entité User (le reviewed)
        @ManyToOne
        @JoinColumn(name = "reviewed_id", nullable = false) // Relation avec la table 'users' (la personne qui reçoit un avis)
        private User reviewed;

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

        public User getReviewer() {
            return reviewer;
        }

        public void setReviewer(User reviewer) {
            this.reviewer = reviewer;
        }

        public User getReviewed() {
            return reviewed;
        }

        public void setReviewed(User reviewed) {
            this.reviewed = reviewed;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Timestamp getReviewDate() {
            return reviewDate;
        }

        public void setReviewDate(Timestamp reviewDate) {
            this.reviewDate = reviewDate;
        }
    }


