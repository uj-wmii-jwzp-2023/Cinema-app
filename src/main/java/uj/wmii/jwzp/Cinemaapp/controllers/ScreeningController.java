package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Screening> getScreenings() {
        return service.getScreenings();
    }

    @PostMapping
    public Screening addScreening(@RequestBody Screening screening) {
        return service.addScreening(screening);
    }

    @DeleteMapping("{screeningId}")
    public String deleteScreening(@PathVariable("screeningId") Long id) {
        return service.deleteScreening(id);
    }

    @PutMapping("{screeningId}")
    public String updateScreening(@PathVariable("screeningId") Long id,
                              @RequestParam String name,
                              @RequestParam CinemaHall hall,
                              @RequestParam List<Movie> movies,
                              @RequestParam Instant startTime,
                              @RequestParam Instant endTime) {
        return service.updateScreening(id, name, hall, movies, startTime, endTime);
    }

    @PatchMapping("{screeningId}")
    public String patchScreening(@PathVariable("screeningId") Long id,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) CinemaHall hall,
                             @RequestParam(required = false) List<Movie> movies,
                             @RequestParam(required = false) Instant startTime,
                             @RequestParam(required = false) Instant endTime) {
        return service.patchScreening(id, name, hall, movies, startTime, endTime);
    }
}
