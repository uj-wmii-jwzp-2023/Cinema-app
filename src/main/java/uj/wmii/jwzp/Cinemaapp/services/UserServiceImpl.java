package uj.wmii.jwzp.Cinemaapp.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.repositories.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.repository = userRepository;
    }


    boolean CorrectEmail(String email) {

        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

        if (email.length() > 0 &&
                matcher.matches()) {

            Optional<User> optionalUser = repository.findUserByEmail(email);
            if (optionalUser.isPresent()) {
                throw new IllegalStateException("Email already taken");
            }

            return true;
        }

        throw new IllegalStateException("Invalid email");
    }

    boolean CorrectName(String name) {
        if (name != null &&
                name.length() > 0) {

            return true;
        }

        throw new IllegalStateException("Invalid name");
    }

    boolean CorrectPassword(String password) {
        if (password != null &&
                password.length() > 5) {

            return true;
        }

        throw new IllegalStateException("Invalid password");
    }


    public List<User> getUsers() {
        return repository.findAll();
    }

    @Override
    public User addUser(User user) {
        if (CorrectEmail(user.getEmail()) && CorrectName(user.getName()) && CorrectPassword(user.getPassword()))
            return repository.save(user);
        else
            throw new IllegalStateException("Cannot create user");
    }

    @Override
    public String deleteUser(Long id) {

        if (!repository.existsById(id)) {
            throw new IllegalStateException("User with id " + id + " does not exist");
        }

        repository.deleteById(id);
        return "User accout with id " + id + " was deleted";
    }

    @Transactional
    public String updateUser(Long id, String email, String name, String password) {
        User user = repository.findById(id).orElse(null);

        if(user == null) {
            User newUser = new User(email, name, password);

            addUser(newUser);
            return "User created";
        }

        String result = "";

        if (CorrectEmail(email) &&
                !Objects.equals(user.getEmail(), email)) {

            user.setEmail(email);
            result += "Email changed\n";
        }

        if (CorrectName(name) &&
                !Objects.equals(user.getName(), name)) {

            user.setName(name);
            result += "Name changed\n";
        }

        if (CorrectPassword(password) &&
                !Objects.equals(user.getPassword(), password)){

            user.setPassword(password);
            result += "Password changed\n";
        }

        if (result.length() == 0)
            return "Nothing changed";
        else
            return result;
    }

    @Transactional
    public String patchUser(Long id, String email, String name, String password) {
        User user = repository.findById(id).orElseThrow(
                () -> new IllegalStateException("User with id " + id + " does not exist")
        );

        String result = "";

        if (email != null) {
            if (CorrectEmail(email) &&
                    !Objects.equals(user.getEmail(), email)) {

                user.setEmail(email);
                result += "Email changed\n";
            }
        }

        if (name != null) {
            if (CorrectName(name) &&
                    !Objects.equals(user.getName(), name)) {

                user.setName(name);
                result += "Name changed\n";
            }
        }

        if (password != null) {
            if (CorrectPassword(password) &&
                    !Objects.equals(user.getPassword(), password)){

                user.setPassword(password);
                result += "Password changed\n";
            }
        }

        if (result.length() == 0)
            return "Nothing changed";
        else
            return result;
    }
}
