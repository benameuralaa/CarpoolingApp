package com.example.carpoolingmanagementsystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RideFlowController {

    @GetMapping("/")
    public String Home() {
        return "Home"; // Renvoie la vue Home.html
    }
    @GetMapping("/Home")
    public String home() {
        return "Home"; // Renvoie la vue Home.html
    }


    @GetMapping("/Search")
    public String search() {
        return "Search"; // Renvoie la vue search.html
    }

    @GetMapping("/AddRide")
    public String addRide() {
        return "AddRide"; // Renvoie la vue AddRide.html
    }

    @GetMapping("/ReserveRide")
    public String reserveRide() {
        return "ReserveRide"; // Renvoie la vue ReserveRide.html
    }

    @GetMapping("/Drivers")
    public String drivers() {
        return "Drivers"; // Renvoie la vue drivers.html
    }

    @GetMapping("/SignIn")
    public String SignIn() {
        return "SignIn"; // Renvoie la vue SignIn.html
    }
    @GetMapping("/SignUp")
    public String SignUp() {
        return "SignUp"; // Renvoie la vue SignUp.html
    }

    @GetMapping("/Contact")
    public String contact() {
        return "Contact"; // Renvoie la vue contact.html
    }
}
