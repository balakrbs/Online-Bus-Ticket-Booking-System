package com.onlinebus.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.onlinebus.Model.Booking;
import com.onlinebus.Model.Users;
import com.onlinebus.Service.BookingService;
import com.onlinebus.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    public String forward(Model model) {
    	model.addAttribute("message", "Please log in");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login() {
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute Users user) {
        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        model.addAttribute("username", username);
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/profile")
    public String viewUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String username = authentication.getName();
        model.addAttribute("username", username);

        try {

            String email = userService.getEmailByUsername(username);
            model.addAttribute("email", email);


            Optional<Users> optionalUser = userService.findByUsername(username);
            if (optionalUser.isPresent()) {
                Users user = optionalUser.get();
                model.addAttribute("user", user);
            }


            List<Booking> bookings = bookingService.getBookingsByEmail(email);
            model.addAttribute("bookings", bookings);

            return "profile";
        } catch (RuntimeException e) {

            return "error"; 
        }
    }

    @GetMapping("/user/update")
    public String showUpdateForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String username = authentication.getName();
        Optional<Users> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            model.addAttribute("user", user);
            return "userupdate";
        }
        return "redirect:/profile";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute("user") Users user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Users> optionalUser = userService.findByUsername(username);
        if (optionalUser.isPresent()) {
            Users existingUser = optionalUser.get();
            existingUser.setFirstname(user.getFirstname());
            existingUser.setLastname(user.getLastname());
            existingUser.setDob(user.getDob());
            existingUser.setGender(user.getGender());
            existingUser.setOccupation(user.getOccupation());
            existingUser.setAddress(user.getAddress());
            existingUser.setPhonenumber(user.getPhonenumber());

            userService.updateUser(existingUser);
        }
        return "redirect:/profile";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }
}
