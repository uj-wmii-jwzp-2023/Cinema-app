package uj.wmii.jwzp.Cinemaapp.services.implementations;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Role;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.repositories.UserRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.UserService;
import uj.wmii.jwzp.Cinemaapp.web.UserRegistrationDto;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User registerUser(UserRegistrationDto userData) {
        if (CorrectEmail(userData.getEmail()) && CorrectName(userData.getName()) && CorrectPassword(userData.getPassword())) {
            User user = new User(userData.getEmail(), userData.getName(), passwordEncoder.encode(userData.getPassword()));
            return repository.save(user);
        }
        else
            throw new IllegalStateException("Cannot create user");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.isEmpty())
            System.out.println("EMPTY_USERNAME");
        else
            System.out.println("username: " + username);

        User user = repository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
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

    public User getUserById(Long id) {
        Optional<User> userOptional = repository.findById(id);
        return userOptional.orElse(null);
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

//    @Override
//    public User registerUser(UserRegistrationDto userData) {
//        if (CorrectEmail(userData.getEmail()) && CorrectName(userData.getName()) && CorrectPassword(userData.getPassword())) {
//            User user = new User(userData.getEmail(), userData.getName(), passwordEncoder.encode(userData.getPassword()));
//            return repository.save(user);
//        }
//        else
//            throw new IllegalStateException("Cannot create user");
//    }

    @Override
    public String deleteUser(Long id) {

        repository.deleteById(id);
        return "User account with id " + id + " was deleted";
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
        User user = getUserById(id);

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
