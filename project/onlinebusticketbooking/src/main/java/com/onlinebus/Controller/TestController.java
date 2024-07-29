package com.onlinebus.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test-profile")
    public String testProfile(Model model) {
        model.addAttribute("email", "test@example.com");
        return "profile";
    }
}
