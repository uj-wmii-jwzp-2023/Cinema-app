package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaController.class);
    private final CinemaService service;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        service = cinemaService;
    }

    @GetMapping("/{cinemaId}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable("cinemaId") Long id) {
        LOGGER.debug("Getting cinema by id: {}", id);

        Cinema cinema = service.getCinemaById(id);

        if (cinema == null) {
            LOGGER.info("Cinema with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Found cinema with id {}: {}", id, cinema);
        return new ResponseEntity<>(cinema, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cinema>> getCinemas() {
        LOGGER.debug("Getting all cinemas");

        List<Cinema> cinemas = service.getCinemas();

        LOGGER.info("Found {} cinemas", cinemas.size());
        return new ResponseEntity<>(cinemas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cinema> addCinema(@RequestBody Cinema cinema) {
        LOGGER.debug("Adding cinema: {}", cinema);

        Cinema addedCinema = service.addCinema(cinema);

        LOGGER.info("Added cinema with id {}: {}", addedCinema.getId(), addedCinema);
        return new ResponseEntity<>(addedCinema, HttpStatus.OK);
    }

    @DeleteMapping("{cinemaId}")
    public ResponseEntity<String> deleteCinema(@PathVariable("cinemaId") Long id) {
        LOGGER.debug("Deleting cinema with id: {}", id);

        Cinema cinema = service.getCinemaById(id);

        if (cinema == null) {
            LOGGER.info("Cinema with id {} not found", id);
            return new ResponseEntity<>("Cinema with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String deletedCinema = service.deleteCinema(id);
        LOGGER.info("Deleted cinema with id {}: {}", id, deletedCinema);
        return new ResponseEntity<>(deletedCinema, HttpStatus.OK);
    }

    @PutMapping("{cinemaId}")
    public ResponseEntity<String> updateCinema(@PathVariable("cinemaId") Long id,
                                               @RequestParam String name,
                                               @RequestParam String address,
                                               @RequestParam List<CinemaHall> cinemaHalls) {
        LOGGER.debug("Updating cinema with id: {}", id);

        String updatedCinema = service.updateCinema(id, name, address, cinemaHalls);

        LOGGER.info("Updated cinema with id {}: {}", id, updatedCinema);
        return new ResponseEntity<>(updatedCinema, HttpStatus.OK);
    }

    @PatchMapping("{cinemaId}")
    public ResponseEntity<String> patchCinema(@PathVariable("cinemaId") Long id,
                                              @RequestParam String name,
                                              @RequestParam String address,
                                              @RequestParam List<CinemaHall> cinemaHalls) {
        LOGGER.debug("Patching cinema with id: {}", id);

        Cinema cinema = service.getCinemaById(id);

        if (cinema == null) {
            LOGGER.info("Cinema with id {} not found", id);
            return new ResponseEntity<>("Cinema with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String patchedCinema = service.patchCinema(id, name, address, cinemaHalls);
        LOGGER.info("Patched cinema with id {}: {}", id, patchedCinema);
        return new ResponseEntity<>(patchedCinema, HttpStatus.OK);
    }

    @GetMapping("/{cinemaId}/movies")
    public ResponseEntity<List<Movie>> getMovies(@PathVariable("cinemaId") Long id) {
        LOGGER.debug("Getting movies for cinema with id: {}", id);

        List<Movie> movies = service.getMovies(id);

        LOGGER.info("Found {} movies for cinema with id {}", movies.size(), id);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{cinemaId}/screenings")
    public ResponseEntity<List<Screening>> getScreeningsByMovie(@PathVariable("cinemaId") Long cinemaId, Movie movie) {
        LOGGER.debug("Getting screenings for cinema with id: {}, movie with id: {}", cinemaId, movie.getId());

        List<Screening> screenings = service.getScreeningsByMovie(cinemaId,movie);

        LOGGER.info("Found {} screenings for cinema with id: {}, movie with id: {}", screenings.size(), cinemaId, movie.getId());
        return ResponseEntity.ok(screenings);
    }
}
