package uj.wmii.jwzp.Cinemaapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import uj.wmii.jwzp.Cinemaapp.controllers.UserController;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    private static final String END_POINT_PATCH = "/users";
    @MockBean
    private UserService userService;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void itShouldReturnCreatedUser() throws Exception {
        User newUser = new User("arturoPL@wp.pl", "Artur", "qwerty1");

        String requestBody = objectMapper.writeValueAsString(newUser);

        when(userService.addUser(any(User.class))).thenReturn(newUser);

        mockMvc.perform(post(END_POINT_PATCH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is(403));
    }

    @Test
    public void itShouldReturnCreatedEmptyUser() throws Exception {
        User newUser = new User();

        String requestBody = objectMapper.writeValueAsString(newUser);

        when(userService.addUser(any(User.class))).thenReturn(newUser);

        mockMvc.perform(post(END_POINT_PATCH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is(403));
    }

}
