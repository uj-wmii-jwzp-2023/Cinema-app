package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {
    private final CinemaService service;

    @Autowired
    public CinemaController(CinemaService cinemaService) {
        service = cinemaService;
    }

    @GetMapping
    public List<Cinema> getCinemas() {
        return service.getCinemas();
    }

    @PostMapping
    public Cinema addCinema(@RequestBody Cinema cinema) {
        return service.addCinema(cinema);
    }

    @DeleteMapping("{cinemaId}")
    public String deleteCinema(@PathVariable("cinemaId") Long id) {
        return service.deleteCinema(id);
    }

    @PutMapping("{cinemaId}")
    public String updateCinema(@PathVariable("cinemaId") Long id,
                               @RequestParam String name,
                               @RequestParam String password,
                               @RequestParam List<CinemaHall> cinemaHalls) {
        return service.updateCinema(id,name,password,cinemaHalls);
    }

    @PatchMapping("{cinemaId}")
    public String patchCinema(@PathVariable("cinemaId") Long id,
                            @RequestParam(required = false) String name,
                            @RequestParam(required = false) String password) {
        return service.patchCinema(id, name, password);
    }
}
