package com.example.carpoolingmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RideFlowController {

    @GetMapping("/Search")
    public String search() {
        return "Search"; // Renvoie la vue search.html
    }
    @GetMapping("/Contact")
    public String contact() {
        return "Contact"; // Renvoie la vue contact.html
    }
    @GetMapping("/ReserveRide")
    public String reserveRide() {
        return "ReserveRide"; // Renvoie la vue ReserveRide.html
    }

    @GetMapping("/Drivers")
    public String drivers() {
        return "Drivers"; // Renvoie la vue drivers.html
    }

}
