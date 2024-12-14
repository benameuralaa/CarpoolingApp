package com.example.carpoolingmanagementsystem.Service;

import com.example.carpoolingmanagementsystem.Entity.User;
import com.example.carpoolingmanagementsystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Méthode registerUser
    public boolean registerUser(User user) {
        // Vérifiez si l'email existe déjà
        if (userRepository.existsByEmail(user.getEmail())) {
            return false; // Échec de l'enregistrement
        }
        // Enregistrer le nouvel utilisateur dans la base de données
        userRepository.save(user);
        return true; //
    }

    // Méthode d'authentification d'un utilisateur
    public User authenticateUser(User user) {
        // Chercher l'utilisateur par son nom
        User existingUser = userRepository.findByEmail(user.getEmail());

        // Vérifier si l'utilisateur existe et si le mot de passe correspond
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return existingUser; // L'utilisateur est authentifié
        }

        return null; // L'authentification a échoué
    }
}
