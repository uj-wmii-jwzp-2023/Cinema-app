package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.ScreeningService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/screenings")
public class ScreeningController {
    private final ScreeningService service;

    @Autowired
    public ScreeningController(ScreeningService screeningService) {
        service = screeningService;
    }


    @GetMapping("/{screeningId}")
    public ResponseEntity<Screening> getScreeningById(@PathVariable("screeningId") Long id) {
        Screening screening = service.getScreeningById(id);

        if(screening == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(screening, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Screening>> getScreenings() {
        return new ResponseEntity<>(
                service.getScreenings(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Screening> addScreening(@RequestBody Screening screening) {
        return new ResponseEntity<>(
                service.addScreening(screening), HttpStatus.OK
        );
    }

    @DeleteMapping("{screeningId}")
    public ResponseEntity<String> deleteScreening(@PathVariable("screeningId") Long id) {
        Screening screening = service.getScreeningById(id);

        if(screening == null)
            return new ResponseEntity<>("Screening with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.deleteScreening(id), HttpStatus.OK);
    }

    @PutMapping("{screeningId}")
    public ResponseEntity<String> updateScreening(@PathVariable("screeningId") Long id,
                              @RequestParam String name,
                              @RequestParam CinemaHall hall,
                              @RequestParam List<Movie> movies,
                              @RequestParam Instant startTime,
                              @RequestParam Instant endTime) {
        
        return new ResponseEntity<>(service.updateScreening(id, name, hall, movies, startTime, endTime), HttpStatus.OK);
    }

    @PatchMapping("{screeningId}")
    public ResponseEntity<String> patchScreening(@PathVariable("screeningId") Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) CinemaHall hall,
                             @RequestParam(required = false) List<Movie> movies,
                             @RequestParam(required = false) Instant startTime,
                             @RequestParam(required = false) Instant endTime) {

        Screening screening = service.getScreeningById(id);

        if(screening == null)
            return new ResponseEntity<>("Screening with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.patchScreening(id, name, hall, movies, startTime, endTime), HttpStatus.OK);
    }
}
