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
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.repositories.CinemaRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaService;
import uj.wmii.jwzp.Cinemaapp.services.implementations.CinemaServiceImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(CinemaService.class)
public class CinemaServiceImplTests {
    @MockBean
    private CinemaRepository cinemaRepository;
    @InjectMocks
    private CinemaServiceImpl cinemaService;

    @Test
    public void AddCinemaWithValidDataShouldReturnSavedCinema() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Cinema newCinema = new Cinema("Bonarka", "Krakow ul. Sloneczna 21", cinemaHalls);

        when(cinemaRepository.save(newCinema)).thenReturn(new Cinema(newCinema.getName(), newCinema.getAddress(), cinemaHalls));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        Cinema created = cinemaService.addCinema(newCinema);

        assertEquals(created.getName(), newCinema.getName());
        assertEquals(created.getAddress(), newCinema.getAddress());
    }

    @ParameterizedTest
    @ValueSource(strings = {""})
    void addCinemaWithInvalidNameShouldThrowException(String name) {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Cinema newCinema = new Cinema(name, "Rzeszow ul. 3 Maja 12", cinemaHalls);

        when(cinemaRepository.save(newCinema)).thenReturn(new Cinema(newCinema.getName(), newCinema.getAddress(), cinemaHalls));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        Assertions.assertThrows(IllegalStateException.class, () -> cinemaService.addCinema(newCinema));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "a", "bb", "ccc", "dddd"})
    void addCinemaWithInvalidAddressShouldThrowException(String address) {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Cinema newCinema = new Cinema( "Bajka", address, cinemaHalls);

        when(cinemaRepository.save(newCinema)).thenReturn(new Cinema(newCinema.getName(), newCinema.getAddress(), cinemaHalls));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        Assertions.assertThrows(IllegalStateException.class, () -> cinemaService.addCinema(newCinema));
    }

    @Test
    void deleteOfExistingCinemaShouldReturnString() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String output = "Cinema with id " + id + " was deleted";

        when(cinemaRepository.existsById(id)).thenReturn(true);
        doNothing().when(cinemaRepository).deleteById(id);
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.deleteCinema(id), output);
    }

    @Test
    void deleteOfNonExistingCinemaShouldThrowException() {
        Long id = 123456L;

        when(cinemaRepository.existsById(id)).thenReturn(false);
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        Assertions.assertThrows(IllegalStateException.class, () -> cinemaService.deleteCinema(id));
    }

    @Test
    void updateNonExistingCinemaShouldCreateOne() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String name = "Sniezka";
        String address = "dfgdjngdf";
        String output = "Cinema created";

        when(cinemaRepository.findById(id)).thenReturn(Optional.empty());
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.updateCinema(id, name, address, cinemaHalls), output);
    }

    @Test
    void updateCinemaByItsDataShouldChangeNothing() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String name = "Sniezka";
        String address = "dfgdjngdf";
        Cinema cinema = new Cinema(name, address, cinemaHalls);
        String output = "Nothing changed";

        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.updateCinema(id, name, address, cinemaHalls), output);
    }

    @Test
    void updateCinemaNameShouldUpdateIt() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String oldName = "Sniezka";
        String newName = "Lajka";
        String address = "zxccxz";
        Cinema cinema = new Cinema(oldName, address, cinemaHalls);
        String output = "Name changed\n";

        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.updateCinema(id, newName, address, cinemaHalls), output);
    }

    @Test
    void updateCinemaAddressShouldUpdateIt() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String name = "Sniezka";
        String oldAddress = "zxccxz";
        String newAddress = "ul. Sloneczna 31";
        Cinema cinema = new Cinema(name, oldAddress, cinemaHalls);
        String output = "Address changed\n";

        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.updateCinema(id, name, newAddress, cinemaHalls), output);
    }

    @Test
    void updateAllCinemaDataShouldUpdateIt() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String oldName = "Sniezka";
        String newName = "Lajka";
        String oldAddress = "zxccxz";
        String newAddress = "ul. Sloneczna 31";
        Cinema cinema = new Cinema(oldName, oldAddress, cinemaHalls);
        String output = "Name changed\n" +
                "Address changed\n";

        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.updateCinema(id, newName, newAddress, cinemaHalls), output);
    }

    @Test
    void patchNonExistingCinemaShouldThrowException() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String name = "Sniezka";
        String address = "dfgdjngdf";

        when(cinemaRepository.findById(id)).thenReturn(Optional.empty());
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        Assertions.assertThrows(IllegalStateException.class, () -> cinemaService.patchCinema(id, name, address, cinemaHalls));
    }

    @Test
    void patchCinemaNameShouldUpdateIt() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String oldName = "Sniezka";
        String newName = "Lajka";
        String address = "zxccxz";
        Cinema cinema = new Cinema(oldName, address, cinemaHalls);
        String output = "Name changed\n";

        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.patchCinema(id, newName, address, cinemaHalls), output);
    }

    @Test
    void patchCinemaAddressShouldUpdateIt() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String name = "Sniezka";
        String oldAddress = "zxccxz";
        String newAddress = "ul. Sloneczna 31";
        Cinema cinema = new Cinema(name, oldAddress, cinemaHalls);
        String output = "Address changed\n";

        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.patchCinema(id, name, newAddress, cinemaHalls), output);
    }

    @Test
    void patchAllUserDataShouldUpdateIt() {
        List<CinemaHall> cinemaHalls = new LinkedList<>();
        Long id = 123456L;
        String oldName = "Sniezka";
        String newName = "Lajka";
        String oldAddress = "zxccxz";
        String newAddress = "ul. Sloneczna 31";
        Cinema cinema = new Cinema(oldName, oldAddress, cinemaHalls);
        String output = "Name changed\n" +
                "Address changed\n";

        when(cinemaRepository.findById(id)).thenReturn(Optional.of(cinema));
        cinemaService = new CinemaServiceImpl(cinemaRepository);

        assertEquals(cinemaService.patchCinema(id, newName, newAddress, cinemaHalls), output);
    }
}
