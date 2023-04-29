package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> getUsers();

    User addUser(User user);

    String deleteUser(Long id);

    String updateUser(Long id, String email, String name, String password);

    String patchUser(Long id, String email, String name, String password);
}
