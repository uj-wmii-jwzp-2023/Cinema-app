package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.MovieService;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.ScreeningService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;


//@RestController
@Controller
@RequestMapping("/screenings")
public class ScreeningController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScreeningController.class);

    private final ScreeningService screeningService;
    private final MovieService movieService;

    @Autowired
    public ScreeningController(ScreeningService screeningService, MovieService movieService) {
        this.screeningService = screeningService;
        this.movieService = movieService;
    }

    @GetMapping("/{screeningId}")
    public ResponseEntity<Screening> getScreeningById(@PathVariable("screeningId") Long id) {
        LOGGER.debug("Getting screening by id: {}", id);

        Screening screening = screeningService.getScreeningById(id);

        if (screening == null) {
            LOGGER.info("Screening with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Found screening with id {}: {}", id, screening);
        return new ResponseEntity<>(screening, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Screening>> getScreenings() {
        LOGGER.debug("Getting all screenings");

        List<Screening> screenings = screeningService.getScreenings();

        LOGGER.info("Found {} screenings", screenings.size());
        return new ResponseEntity<>(screenings, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Screening> addScreening(@RequestBody Screening screening) {
        LOGGER.debug("Adding screening: {}", screening);

        Screening addedScreening = screeningService.addScreening(screening);

        if (addedScreening == null) {
            LOGGER.info("Failed to add screening: {}", screening);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        LOGGER.info("Added screening with id {}: {}", addedScreening.getId(), addedScreening);
        return new ResponseEntity<>(addedScreening, HttpStatus.OK);
    }

    @DeleteMapping("{screeningId}")
    public ResponseEntity<String> deleteScreening(@PathVariable("screeningId") Long id) {
        LOGGER.debug("Deleting screening with id: {}", id);

        Screening screening = screeningService.getScreeningById(id);

        if (screening == null) {
            LOGGER.info("Screening with id {} not found", id);
            return new ResponseEntity<>("Screening with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String deletedScreening = screeningService.deleteScreening(id);
        LOGGER.info("Deleted screening with id {}: {}", id, deletedScreening);
        return new ResponseEntity<>(deletedScreening, HttpStatus.OK);
    }

    @PutMapping("{screeningId}")
    public ResponseEntity<String> updateScreening(@PathVariable("screeningId") Long id,
                                                  @RequestParam String name,
                                                  @RequestParam CinemaHall hall,
                                                  @RequestParam List<Movie> movies,
                                                  @RequestParam LocalDateTime startTime,
                                                  @RequestParam LocalDateTime endTime) {
        LOGGER.debug("Updating screening with id: {}", id);

        String updatedScreening = screeningService.updateScreening(id, name, hall, movies, startTime, endTime);

        if (updatedScreening == null) {
            LOGGER.info("Failed to update screening with id {}: {}", id, updatedScreening);
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        LOGGER.info("Updated screening with id {}: {}", id, updatedScreening);
        return new ResponseEntity<>(updatedScreening, HttpStatus.OK);
    }

    @PatchMapping("{screeningId}")
    public ResponseEntity<String> patchScreening(@PathVariable("screeningId") Long id,
                                                 @RequestParam(required = false) String name,
                                                 @RequestParam(required = false) CinemaHall hall,
                                                 @RequestParam(required = false) List<Movie> movies,
                                                 @RequestParam(required = false) LocalDateTime startTime,
                                                 @RequestParam(required = false) LocalDateTime endTime) {
        LOGGER.debug("Patching screening with id: {}", id);

        Screening screening = screeningService.getScreeningById(id);

        if (screening == null) {
            LOGGER.info("Screening with id {} not found", id);
            return new ResponseEntity<>("Screening with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String patchedScreening = screeningService.patchScreening(id, name, hall, movies, startTime, endTime);
        LOGGER.info("Patched screening with id {}: {}", id, patchedScreening);
        return new ResponseEntity<>(patchedScreening, HttpStatus.OK);
    }

    @GetMapping("/create")
    public String showCreateScreeningForm(Model model) {
        model.addAttribute("screening", new Screening());

        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);

        for (Movie movie : movies) {
            LOGGER.info("Found movie: {}", movie);
        }

        return "CreateScreeningForm";
    }

    @PostMapping("/create")
    public ResponseEntity<Screening> createScreening(@ModelAttribute("screening") Screening screening) {
        List<Movie> movies = screening.getMovies();

        for (Movie movie : movies) {
            movie.addScreening(screening);
            LOGGER.info("Added screening to: {}", movie);
        }

        screeningService.addScreening(screening);
        LOGGER.info("Created screening: {}", screening);
        return new ResponseEntity<>(screening, HttpStatus.OK);
    }
}