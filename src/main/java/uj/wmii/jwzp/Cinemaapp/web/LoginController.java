package uj.wmii.jwzp.Cinemaapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showLoginForm() {
        return "LoginForm";
    }

    @GetMapping("/")
    public String home() { return "Index"; }
}