package com.example.carpoolingmanagementsystem.Controller;


import com.example.carpoolingmanagementsystem.Entity.User;
import com.example.carpoolingmanagementsystem.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Affichage de la page sign up
    @GetMapping("/SignUp")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "SignUp";
    }

    // Traitement du formulaire sign up
    @PostMapping("/SignUp")
    public String registerUser(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {

        boolean isRegistered = userService.registerUser(user);

        if (!isRegistered) {
            redirectAttributes.addFlashAttribute("EmailExistsErrorMessage", "Email already exists! Please try again.");
            return "redirect:/SignUp";
        }

        redirectAttributes.addFlashAttribute("RegisteredSuccessfullyMessage", "Registered successfully! Please sign in.");
        return "redirect:/SignIn";
    }

    // Affichage de la page de connexion
    @GetMapping("/SignIn")
    public String signInPage(Model model) {
        model.addAttribute("user", new User()); // Objet User pour le formulaire
        return "SignIn";
    }

    // Traitement du formulaire de connexion
    @PostMapping("/SignIn")
    public String signIn(@ModelAttribute User user, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        User authenticatedUser = userService.authenticateUser(user);

        if (authenticatedUser != null) {
            // Stockez l'utilisateur dans la session
            session.setAttribute("user", authenticatedUser);
            session.setAttribute("loggedInUserId", authenticatedUser.getId());
            session.setAttribute("loggedInUserName", authenticatedUser.getName());

            // Stockez le rôle de l'utilisateur dans la session
            if ("DRIVER".equals(authenticatedUser.getRole())) {
                session.setAttribute("role", "DRIVER");
            } else if ("PASSENGER".equals(authenticatedUser.getRole())) {
                session.setAttribute("role", "PASSENGER");
            }
            // Ajouter un message de succès dans le modèle
            redirectAttributes.addFlashAttribute("LoggedInMessage", "You have successfully logged in!");
            return "redirect:/Home"; // Redirection vers la page d'accueil
        }

        // Message d'erreur pour des identifiants invalides
        redirectAttributes.addFlashAttribute("InvalidcredentialsMessage", "Invalid credentials! Please try again.");
        return "redirect:/SignIn";
    }

    //Déconnexion de l'utilisateur
    @GetMapping("/Logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalide la session de l'utilisateur
        return "redirect:/Home"; // Redirection vers la page d'accueil
    }

    @GetMapping("/Home")
    public String homePage(HttpSession session, Model model) {
        // Récupérer le rôle de l'utilisateur depuis la session
        String role = (String) session.getAttribute("role");

        // Ajouter le rôle au modèle si nécessaire (par exemple, pour afficher des éléments spécifiques dans la vue)
        model.addAttribute("role", role);
        return "Home";
    }
    @GetMapping("/")
    public String Home() {
        return "Home";
    }

}
