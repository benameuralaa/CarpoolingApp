package com.example.carpoolingmanagementsystem.Repository;

import com.example.carpoolingmanagementsystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

