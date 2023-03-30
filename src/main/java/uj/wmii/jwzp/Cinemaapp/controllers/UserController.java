package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        service = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return service.addUser(user);
    }

    @DeleteMapping("{userId}")
    public String deleteUser(@PathVariable("userId") Long id) {
        return service.deleteUser(id);
    }

    @PutMapping("{userId}")
    public String updateUser(@PathVariable("userId") Long id,
                             @RequestParam String email,
                             @RequestParam String name,
                             @RequestParam String password) {
        return service.updateUser(id, email, name, password);
    }

    @PatchMapping("{userId}")
    public String patchUser(@PathVariable("userId") Long id,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String password) {
        return service.patchUser(id, email, name, password);
    }
}
