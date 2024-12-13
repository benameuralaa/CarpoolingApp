package com.example.carpoolingmanagementsystem.Repository;

import com.example.carpoolingmanagementsystem.Entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}

