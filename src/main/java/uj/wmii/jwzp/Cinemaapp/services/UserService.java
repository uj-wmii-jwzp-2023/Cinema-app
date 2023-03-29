package uj.wmii.jwzp.Cinemaapp.services;

import uj.wmii.jwzp.Cinemaapp.models.User;

import java.util.List;

public interface UserService {
    List<User> users();

    User addUser(User user);

    void deleteUser(Long id);

    User updateUser(Long id,User user);

    User patchUser(Long id, User user);
}
