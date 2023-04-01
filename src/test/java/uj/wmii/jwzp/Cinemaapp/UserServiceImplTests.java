package uj.wmii.jwzp.Cinemaapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.repositories.UserRepository;
import uj.wmii.jwzp.Cinemaapp.services.UserService;
import uj.wmii.jwzp.Cinemaapp.services.UserServiceImpl;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(UserService.class)
public class UserServiceImplTests {
    @MockBean
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void AddUserWithValidDataShouldReturnSavedUser() {
        User newUser = new User("arturoPL@wp.pl", "Artur", "qwerty1");

        when(userRepository.findUserByEmail(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(new User(newUser.getEmail(), newUser.getName(), newUser.getPassword()));
        userService = new UserServiceImpl(userRepository);

        User created = userService.addUser(newUser);

        assertEquals(created.getEmail(), newUser.getEmail());
        assertEquals(created.getName(), newUser.getName());
        assertEquals(created.getPassword(), newUser.getPassword());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abc.pl", "@gmail.com", "asd@dsa"})
    void addUserWithInvalidEmailShouldThrowException(String email) {
        User newUser = new User(email, "Artur", "qwerty1");

        when(userRepository.findUserByEmail(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(new User(newUser.getEmail(), newUser.getName(), newUser.getPassword()));
        userService = new UserServiceImpl(userRepository);

        Assertions.assertThrows(IllegalStateException.class, () -> userService.addUser(newUser));
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    void addUserWithInvalidNameShouldThrowException(String name) {
        User newUser = new User("correct@email.com", name, "qwerty1");

        when(userRepository.findUserByEmail(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(new User(newUser.getEmail(), newUser.getName(), newUser.getPassword()));
        userService = new UserServiceImpl(userRepository);

        Assertions.assertThrows(IllegalStateException.class, () -> userService.addUser(newUser));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "bb", "ccc", "dddd"})
    void addUserWithInvalidPasswordShouldThrowException(String password) {
        User newUser = new User("correct@email.com", "Artur", password);

        when(userRepository.findUserByEmail(any(String.class))).thenReturn(Optional.empty());
        when(userRepository.save(newUser)).thenReturn(new User(newUser.getEmail(), newUser.getName(), newUser.getPassword()));
        userService = new UserServiceImpl(userRepository);

        Assertions.assertThrows(IllegalStateException.class, () -> userService.addUser(newUser));
    }

    @Test
    void deleteOfExistingUserShouldReturnString() {
        Long id = 123456L;
        String output = "User account with id " + id + " was deleted";

        when(userRepository.existsById(id)).thenReturn(true);
        doNothing().when(userRepository).deleteById(id);
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.deleteUser(id), output);
    }

    @Test
    void deleteOfNonExistingUserShouldThrowException() {
        Long id = 123456L;

        when(userRepository.existsById(id)).thenReturn(false);
        userService = new UserServiceImpl(userRepository);

        Assertions.assertThrows(IllegalStateException.class, () -> userService.deleteUser(id));
    }

    @Test
    void updateNonExistingUserShouldCreateOne() {
        Long id = 123456L;
        String email = "correct@email.com";
        String name = "Alojzy";
        String password = "zxccxz";
        String output = "User created";

        when(userRepository.findById(id)).thenReturn(Optional.empty());
        userService = new UserServiceImpl(userRepository);
        //doNothing().when(userService).addUser(any(User.class));

        assertEquals(userService.updateUser(id, email, name, password), output);
    }

    @Test
    void updateUserByItsDataShouldChangeNothing() {
        Long id = 123456L;
        String email = "correct@email.com";
        String name = "Alojzy";
        String password = "zxccxz";
        User user = new User(email, name, password);
        String output = "Nothing changed";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, email, name, password), output);
    }

    @Test
    void updateUserEmailShouldUpdateIt() {
        Long id = 123456L;
        String oldEmail = "correct@email.com";
        String newEmail = "updated@email.com";
        String name = "Alojzy";
        String password = "zxccxz";
        User user = new User(oldEmail, name, password);
        String output = "Email changed\n";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, newEmail, name, password), output);
    }

    @Test
    void updateUserNameShouldUpdateIt() {
        Long id = 123456L;
        String email = "correct@email.com";
        String oldName = "Alojzy";
        String newName = "Barnaba";
        String password = "zxccxz";
        User user = new User(email, oldName, password);
        String output = "Name changed\n";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, email, newName, password), output);
    }

    @Test
    void updateUserPasswordShouldUpdateIt() {
        Long id = 123456L;
        String email = "correct@email.com";
        String name = "Alojzy";
        String oldPassword = "zxccxz";
        String newPassword = "ABCDEFG12345";
        User user = new User(email, name, oldPassword);
        String output = "Password changed\n";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, email, name, newPassword), output);
    }

    @Test
    void updateAllUserDataShouldUpdateIt() {
        Long id = 123456L;
        String oldEmail = "correct@email.com";
        String newEmail = "myNewEmail@email.com";
        String oldName = "Alojzy";
        String newName = "Kajfasz";
        String oldPassword = "zxccxz";
        String newPassword = "1234567890";
        User user = new User(oldEmail, oldName, oldPassword);
        String output = "Email changed\n" +
                "Name changed\n" +
                "Password changed\n";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, newEmail, newName, newPassword), output);
    }

    @Test
    void patchNonExistingUserShouldThrowException() {
        Long id = 123456L;
        String email = "correct@email.com";
        String name = "Alojzy";
        String password = "zxccxz";

        when(userRepository.findById(id)).thenReturn(Optional.empty());
        userService = new UserServiceImpl(userRepository);
        //doNothing().when(userService).addUser(any(User.class));

        Assertions.assertThrows(IllegalStateException.class, () -> userService.patchUser(id, name, email, password));
    }


    @Test
    void patchUserEmailShouldUpdateIt() {
        Long id = 123456L;
        String oldEmail = "correct@email.com";
        String newEmail = "updated@email.com";
        String name = "Alojzy";
        String password = "zxccxz";
        User user = new User(oldEmail, name, password);
        String output = "Email changed\n";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, newEmail, name, password), output);
    }

    @Test
    void patchUserNameShouldUpdateIt() {
        Long id = 123456L;
        String email = "correct@email.com";
        String oldName = "Alojzy";
        String newName = "Barnaba";
        String password = "zxccxz";
        User user = new User(email, oldName, password);
        String output = "Name changed\n";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, email, newName, password), output);
    }

    @Test
    void patchUserPasswordShouldUpdateIt() {
        Long id = 123456L;
        String email = "correct@email.com";
        String name = "Alojzy";
        String oldPassword = "zxccxz";
        String newPassword = "ABCDEFG12345";
        User user = new User(email, name, oldPassword);
        String output = "Password changed\n";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, email, name, newPassword), output);
    }

    @Test
    void patchAllUserDataShouldUpdateIt() {
        Long id = 123456L;
        String oldEmail = "correct@email.com";
        String newEmail = "myNewEmail@email.com";
        String oldName = "Alojzy";
        String newName = "Kajfasz";
        String oldPassword = "zxccxz";
        String newPassword = "1234567890";
        User user = new User(oldEmail, oldName, oldPassword);
        String output = "Email changed\n" +
                "Name changed\n" +
                "Password changed\n";

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        userService = new UserServiceImpl(userRepository);

        assertEquals(userService.updateUser(id, newEmail, newName, newPassword), output);
    }
}
