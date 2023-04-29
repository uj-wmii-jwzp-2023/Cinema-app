package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") Long id) {
        Movie movie = service.getMovieById(id);

        if(movie == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies() {
        return new ResponseEntity<>(
                service.getMovies(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(
                service.addMovie(movie), HttpStatus.OK
        );
    }

    @DeleteMapping("{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Long id) {
        Movie movie = service.getMovieById(id);

        if(movie == null)
            return new ResponseEntity<>("Movie with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.deleteMovie(id), HttpStatus.OK);
    }

    @PutMapping("{movieId}")
    public ResponseEntity<String> updateMovie(@PathVariable("movieId") Long id,
                              @RequestParam String name,
                              @RequestParam Duration duration,
                              @RequestParam String description,
                              @RequestParam String directors,
                              @RequestParam List<Screening> screenings) {

        return new ResponseEntity<>(service.updateMovie(id, name, duration, description, directors, screenings), HttpStatus.OK);
    }

    @PatchMapping("{movieId}")
    public ResponseEntity<String> patchMovie(@PathVariable("movieId") Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Duration duration,
                             @RequestParam(required = false) String description,
                             @RequestParam(required = false) String directors,
                             @RequestParam(required = false) List<Screening> screenings) {
        
        Movie movie = service.getMovieById(id);

        if(movie == null)
            return new ResponseEntity<>("Movie with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.patchMovie(id, name, duration, description, directors, screenings), HttpStatus.OK);
    }
}
