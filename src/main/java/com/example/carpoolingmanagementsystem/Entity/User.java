package com.example.carpoolingmanagementsystem.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

    @Entity
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;
        private String password;
        private String phone;
        private String role;
        public enum Role {
            DRIVER, PASSENGER
        }
        // Getters and setters
        public Long getId() {
            return id;
        }
        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            // Valider ici si le rôle est l'une des valeurs valides
            if (role != null && (role.equalsIgnoreCase("DRIVER") || role.equalsIgnoreCase("PASSENGER"))) {
                this.role = role.toUpperCase(); // Normaliser la casse (optionnel)
            } else {
                throw new IllegalArgumentException("Invalid role value");
            }
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }


