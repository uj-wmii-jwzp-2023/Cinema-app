package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import org.springframework.security.core.userdetails.UserDetails;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.DataTransferObjects.UserRegistrationDTO;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.math.BigDecimal;
import java.util.List;

public interface UserService extends UserDetailsService {

    User getUserById(Long id);

    UserDetails loadUserByUsername(String username);

    User getUserByEmail(String email);

    List<User> getUsers();

    User addUser(User user);

    User registerUser(UserRegistrationDTO user);

    String deleteUser(Long id);

    String updateUser(Long id, String email, String name, String password);

    String patchUser(Long id, String email, String name, String password);

    String addBalance(User user, BigDecimal balance);
}
