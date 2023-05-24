package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.web.UserRegistrationDto;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getUserById(Long id);

    List<User> getUsers();

    User addUser(User user);

    User registerUser(UserRegistrationDto user);

    String deleteUser(Long id);

    String updateUser(Long id, String email, String name, String password);

    String patchUser(Long id, String email, String name, String password);
}
