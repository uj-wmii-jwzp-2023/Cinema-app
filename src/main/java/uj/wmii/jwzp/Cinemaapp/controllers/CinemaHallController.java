package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaHallService;

import java.util.List;

@RestController
@RequestMapping("/cinemaHalls")
public class CinemaHallController {
    private final CinemaHallService service;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaService) {
        service = cinemaService;
    }

    @GetMapping
    public List<CinemaHall> getCinemaHalls() {
        return service.getCinemaHalls();
    }

    @PostMapping
    public CinemaHall addCinemaHall(@RequestBody CinemaHall cinema) {
        return service.addCinemaHall(cinema);
    }

    @DeleteMapping("{cinemaId}")
    public String deleteCinemaHall(@PathVariable("cinemaId") Long id) {
        return service.deleteCinemaHall(id);
    }

    @PutMapping("{cinemaId}")
    public String updateCinemaHall(@PathVariable("cinemaId") Long id,
                               @RequestParam Cinema cinema,
                               @RequestParam List<Seat> seats) {
        return service.updateCinemaHall(id, cinema, seats);
    }

    @PatchMapping("{cinemaId}")
    public String patchCinemaHall(@PathVariable("cinemaId") Long id,
                                  @RequestParam(required = false) Cinema cinema,
                                  @RequestParam(required = false) List<Seat> seats) {
        return service.patchCinemaHall(id, cinema, seats);
    }
}
