package uj.wmii.jwzp.Cinemaapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.UserService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        service = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) {
        LOGGER.debug("Getting user by id: {}", id);

        User user = service.getUserById(id);

        if (user == null) {
            LOGGER.info("User with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Found user with id {}: {}", id, user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        LOGGER.debug("Getting all users");

        List<User> users = service.getUsers();

        LOGGER.info("Found users: {}", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        LOGGER.debug("Adding user: {}", user);

        User addedUser = service.addUser(user);

        LOGGER.info("Added user with id {}: {}", addedUser.getId(), addedUser);
        return new ResponseEntity<>(addedUser, HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {
        LOGGER.debug("Deleting user with id: {}", id);

        User user = service.getUserById(id);

        if (user == null) {
            LOGGER.info("User with id {} not found", id);
            return new ResponseEntity<>("User with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String deletedUser = service.deleteUser(id);
        LOGGER.info("Deleted user with id {}: {}", id, deletedUser);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long id,
                                             @RequestParam String email,
                                             @RequestParam String name,
                                             @RequestParam String password) {
        LOGGER.debug("Updating user with id: {}", id);

        String updatedUser = service.updateUser(id, email, name, password);

        LOGGER.info("Updated user with id {}: {}", id, updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping("{userId}")
    public ResponseEntity<String> patchUser(@PathVariable("userId") Long id,
                                            @RequestParam(required = false) String email,
                                            @RequestParam(required = false) String name,
                                            @RequestParam(required = false) String password) {
        LOGGER.debug("Patching user with id: {}", id);

        User user = service.getUserById(id);

        if (user == null) {
            LOGGER.info("User with id {} not found", id);
            return new ResponseEntity<>("User with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String patchedUser = service.patchUser(id, email, name, password);
        LOGGER.info("Patched user with id {}: {}", id, patchedUser);
        return new ResponseEntity<>(patchedUser, HttpStatus.OK);
    }

    @PostMapping("{userId}/balance")
    public ResponseEntity<String> addBalance(@PathVariable("userId") Long id,
                                             @RequestParam BigDecimal balance) {
        LOGGER.debug("Adding {} balance to user with id: {}",balance,id);

        User user = service.getUserById(id);

        if (user == null) {
            LOGGER.info("User with id {} not found", id);
            return new ResponseEntity<>("User with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String updatedUser = service.addBalance(user,balance);
        LOGGER.info("Patched user with id {}: {}", id, updatedUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
