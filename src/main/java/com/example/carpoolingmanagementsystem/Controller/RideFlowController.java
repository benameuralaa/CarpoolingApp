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


    @GetMapping("/search")
    public String search() {
        return "search"; // Renvoie la vue search.html
    }

    @GetMapping("/AddRide")
    public String addRide() {
        return "AddRide"; // Renvoie la vue AddRide.html
    }

    @GetMapping("/ReserveRide")
    public String reserveRide() {
        return "ReserveRide"; // Renvoie la vue ReserveRide.html
    }

    @GetMapping("/drivers")
    public String drivers() {
        return "drivers"; // Renvoie la vue drivers.html
    }

    @GetMapping("/SignIn")
    public String SignIn() {
        return "SignIn"; // Renvoie la vue SignIn.html
    }
    @GetMapping("/SignUp")
    public String SignUp() {
        return "SignUp"; // Renvoie la vue SignUp.html
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact"; // Renvoie la vue contact.html
    }
}
