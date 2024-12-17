package com.example.carpoolingmanagementsystem.Controller;

import com.example.carpoolingmanagementsystem.Entity.Booking;
import com.example.carpoolingmanagementsystem.Entity.Ride;
import com.example.carpoolingmanagementsystem.Entity.User;
import com.example.carpoolingmanagementsystem.Repository.BookingRepository;
import com.example.carpoolingmanagementsystem.Repository.RideRepository;
import com.example.carpoolingmanagementsystem.Service.RideService;
import com.example.carpoolingmanagementsystem.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RideController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private BookingRepository bookingRepository;

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
    // Afficher Rides dans la page d'accueil
    @GetMapping("/Rides")
    public String showRides(Model model) {
        // Récupérer les trajets depuis le service
        List<Ride> rides = rideService.getAllRides();
        model.addAttribute("rides", rides);
        return "Rides";
    }
    // Search for rides based on the provided parameters
    @GetMapping("/searchRides")
    public String searchRides(@RequestParam(required = false) String departure,
                              @RequestParam(required = false) String destination,
                              @RequestParam(required = false) String date,
                              @RequestParam(required = false) Integer passengers, Model model) {
        List<Ride> rides = new ArrayList<>();
        boolean searchPerformed = false;

        // Vérifie si au moins un paramètre est fourni
        if ((departure != null && !departure.trim().isEmpty()) ||
                (destination != null && !destination.trim().isEmpty()) ||
                (date != null && !date.trim().isEmpty()) ||
                passengers != null) {

            searchPerformed = true;
            LocalDateTime departureTime = null;

            // Convert date string to LocalDateTime (defaulting to midnight if date is provided)
            if (date != null && !date.isEmpty()) {
                departureTime = LocalDateTime.parse(date + "T00:00:00");
            }

            // Appelle le service pour la recherche des rides
            rides = rideService.searchRides(departure, destination, departureTime, passengers);
        }

        // Ajouter les attributs au modèle
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        model.addAttribute("date", date);
        model.addAttribute("passengers", passengers);
        model.addAttribute("rides", rides);
        model.addAttribute("searchPerformed", searchPerformed); // Indique qu'une recherche a été effectuée

        return "Search";  // Retourne la page de recherche
    }
    // Affichage de la page Reserve Ride
    @GetMapping("/ReserveRide")
    public String reserveride(Model model) {
        model.addAttribute("ride", new Ride());
        return "ReserveRide";
    }
}
