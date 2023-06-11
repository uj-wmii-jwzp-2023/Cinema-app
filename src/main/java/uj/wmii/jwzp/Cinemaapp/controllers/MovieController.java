package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;


@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    private final MovieService service;

    @Autowired
    public MovieController(MovieService movieService) {
        service = movieService;
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") Long id) {
        LOGGER.debug("Getting movie by id: {}", id);

        Movie movie = service.getMovieById(id);

        if (movie == null) {
            LOGGER.info("Movie with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Found movie with id {}: {}", id, movie);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        LOGGER.debug("Getting all movies");

        List<Movie> movies = service.getMovies();

        LOGGER.info("Found {} movies", movies.size());
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        LOGGER.debug("Adding movie: {}", movie);

        Movie addedMovie = service.addMovie(movie);

        LOGGER.info("Added movie with id {}: {}", addedMovie.getId(), addedMovie);
        return new ResponseEntity<>(addedMovie, HttpStatus.OK);
    }

    @PostMapping("/addMovies")
    public ResponseEntity<Integer> addMovies(@RequestBody List<Movie> movies) {
        for (Movie movie : movies) {
            service.addMovie(movie);
        }

        return new ResponseEntity<>(movies.size(), HttpStatus.OK);
    }

    @DeleteMapping("{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Long id) {
        LOGGER.debug("Deleting movie with id: {}", id);

        Movie movie = service.getMovieById(id);

        if (movie == null) {
            LOGGER.info("Movie with id {} not found", id);
            return new ResponseEntity<>("Movie with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String deletedMovie = service.deleteMovie(id);
        LOGGER.info("Deleted movie with id {}: {}", id, deletedMovie);
        return new ResponseEntity<>(deletedMovie, HttpStatus.OK);
    }

    @PutMapping("{movieId}")
    public ResponseEntity<String> updateMovie(@PathVariable("movieId") Long id,
                                              @RequestParam String name,
                                              @RequestParam Duration duration,
                                              @RequestParam String description,
                                              @RequestParam String directors,
                                              @RequestParam List<Screening> screenings) {
        LOGGER.debug("Updating movie with id: {}", id);

        String updatedMovie = service.updateMovie(id, name, duration, description, directors, screenings);

        LOGGER.info("Updated movie with id {}: {}", id, updatedMovie);
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }

    @PatchMapping("{movieId}")
    public ResponseEntity<String> patchMovie(@PathVariable("movieId") Long id,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) Duration duration,
                                             @RequestParam(required = false) String description,
                                             @RequestParam(required = false) String directors,
                                             @RequestParam(required = false) List<Screening> screenings) {
        LOGGER.debug("Patching movie with id: {}", id);

        Movie movie = service.getMovieById(id);

        if (movie == null) {
            LOGGER.info("Movie with id {} not found", id);
            return new ResponseEntity<>("Movie with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String patchedMovie = service.patchMovie(id, name, duration, description, directors, screenings);

        LOGGER.info("Patched movie with id {}: {}", id, patchedMovie);
        return new ResponseEntity<>(patchedMovie, HttpStatus.OK);
    }
}