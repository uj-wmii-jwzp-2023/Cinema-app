package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService userService) {
        service = userService;
    }

    @GetMapping
    public List<User> users() {
        return service.users();
    }

    @PostMapping
    public User addUser(User user) {
        return service.addUser(user);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable("userId") Long id) {
        service.deleteUser(id);
    }

    @PutMapping("{userId}")
    public User updateUser(@PathVariable("userId") Long id, User user) {
        return service.updateUser(id,user);
    }

    @PatchMapping("{userId}")
    public User patchUser(@PathVariable("userId") Long id, User user) {
        return service.patchUser(id, user);
    }
}
