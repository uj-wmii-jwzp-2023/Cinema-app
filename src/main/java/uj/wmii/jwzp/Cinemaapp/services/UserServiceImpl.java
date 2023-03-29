package uj.wmii.jwzp.Cinemaapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository repository;

    public UserServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public List<User> users() {
        return repository.findAll();
    }

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }

    @Override
    public User updateUser(Long id,User user) {
        Optional<User> existingUser = repository.findById(id);

        if(existingUser.isEmpty()) {
            return addUser(user);
        }

        User user1 = existingUser.get();
        user1.setName(user.getName());

        return repository.save(user1);
    }

    @Override
    public User patchUser(Long id, User user) {
        Optional<User> existingUser = repository.findById(id);

        if(existingUser.isEmpty()) {
            return addUser(user);
        }

        User user1 = existingUser.get();
        if(user1.getName() != null && !user1.getName().equals("")) {
            user1.setName(user.getName());
        }

        return repository.save(user1);
    }


}
