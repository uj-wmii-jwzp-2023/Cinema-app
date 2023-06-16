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
import uj.wmii.jwzp.Cinemaapp.controllers.CinemaController;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaService;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CinemaController.class)
public class CinemaControllerTests {
    private static final String END_POINT_PATCH = "/cinemas";
    @MockBean
    private CinemaService cinemaService;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void itShouldReturnCreatedCinema() throws Exception {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Cinema newCinema = new Cinema("Bonarka", "Krakow ul. 3 Maja", cinemaHalls);

        String requestBody = objectMapper.writeValueAsString(newCinema);

        when(cinemaService.addCinema(any(Cinema.class))).thenReturn(newCinema);

        mockMvc.perform(post(END_POINT_PATCH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is(403));
    }

    @Test
    public void itShouldReturnCreatedEmptyCinema() throws Exception {
        Cinema newCinema = new Cinema();

        String requestBody = objectMapper.writeValueAsString(newCinema);

        when(cinemaService.addCinema(any(Cinema.class))).thenReturn(newCinema);

        mockMvc.perform(post(END_POINT_PATCH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is(403));
    }
}
