package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService userService) {
        service = userService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) {
        User user = service.getUserById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(
                service.getUsers(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity<>(
                service.addUser(user), HttpStatus.OK
        );
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id) {
        User user = service.getUserById(id);

        if(user == null)
            return new ResponseEntity<>("User with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
    }

    @PutMapping("{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long id,
                             @RequestParam String email,
                             @RequestParam String name,
                             @RequestParam String password) {

        return new ResponseEntity<>(service.updateUser(id, email, name, password), HttpStatus.OK);
    }

    @PatchMapping("{userId}")
    public ResponseEntity<String> patchUser(@PathVariable("userId") Long id,
                            @RequestParam(required = false) String email,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String password) {

        User user = service.getUserById(id);

        if(user == null)
            return new ResponseEntity<>("User with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.patchUser(id, email, name, password), HttpStatus.OK);
    }
}
