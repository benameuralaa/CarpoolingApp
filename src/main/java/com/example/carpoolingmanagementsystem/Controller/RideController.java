package com.example.carpoolingmanagementsystem.Controller;

import com.example.carpoolingmanagementsystem.Entity.Ride;
import com.example.carpoolingmanagementsystem.Entity.User;
import com.example.carpoolingmanagementsystem.Service.RideService;
import com.example.carpoolingmanagementsystem.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideService rideService; // Injection du service RideService

    @Autowired
    private UserService userService;

    // Afficher le formulaire AddRide
    @GetMapping("/AddRide")
    public String showAddRideForm(Model model) {
        // Ajout d'un objet Ride vide au modèle pour le formulaire
        model.addAttribute("ride", new Ride());
        return "AddRide";
    }

    // Soumission du formulaire AddRide
    @PostMapping("/AddRide")
    public String addRide(@ModelAttribute Ride ride, HttpSession session, RedirectAttributes redirectAttributes) {
        // Récupérer les informations du conducteur depuis la session
        Long driverId = (Long) session.getAttribute("loggedInUserId");

        // Récupérer l'entité User correspondante
        User driver = userService.findUserById(driverId);

        // Associer le conducteur et son nom au trajet
        ride.setDriver(driver);               // Relation avec User
        ride.setDriverName(driver.getName()); // Stocker le nom directement dans la base

        // Enregistrer le trajet
        rideService.saveRide(ride);

        // Ajouter un message de succès
        redirectAttributes.addFlashAttribute("RideAddedSuccessfully", "Ride added successfully!");
        return "redirect:/Rides";

    }
    // Afficher les rides dans la page d'accueil
    @GetMapping("/Rides")
    public String showRides(Model model) {
        // Récupérer les trajets depuis le service
        List<Ride> rides = rideService.getAllRides();
        model.addAttribute("rides", rides);
        return "Rides";
    }



}
