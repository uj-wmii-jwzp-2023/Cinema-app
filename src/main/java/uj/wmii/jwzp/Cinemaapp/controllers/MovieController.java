package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.MovieService;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService service;

    @Autowired
    public MovieController(MovieService movieService) {
        service = movieService;
    }

    @GetMapping
    public List<Movie> getMovies() {
        return service.getMovies();
    }

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return service.addMovie(movie);
    }

    @DeleteMapping("{movieId}")
    public String deleteMovie(@PathVariable("movieId") Long id) {
        return service.deleteMovie(id);
    }

    @PutMapping("{movieId}")
    public String updateMovie(@PathVariable("movieId") Long id,
                              @RequestParam String name,
                              @RequestParam Duration duration,
                              @RequestParam String description,
                              @RequestParam String directors,
                              @RequestParam List<Screening> screenings) {
        return service.updateMovie(id, name, duration, description, directors, screenings);
    }

    @PatchMapping("{movieId}")
    public String patchMovie(@PathVariable("movieId") Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Duration duration,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String directors,
                             @RequestParam(required = false) List<Screening> screenings) {
        return service.patchMovie(id, name, duration, description, directors, screenings);
    }
}
