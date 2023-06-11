package uj.wmii.jwzp.Cinemaapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uj.wmii.jwzp.Cinemaapp.services.interfaces.UserService;


@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

    private final UserService userService;

    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm() {
        LOGGER.info("Displaying registration form");
        return "RegistrationForm";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
        userService.registerUser(registrationDto);
        LOGGER.info("Registered user: {}", registrationDto.getEmail());
        return "redirect:/registration?success";
    }
}

