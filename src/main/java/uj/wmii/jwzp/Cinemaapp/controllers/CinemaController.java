package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
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


    @GetMapping("/{cinemaId}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable("cinemaId") Long id) {
        Cinema cinema = service.getCinemaById(id);

        if(cinema == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(cinema, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cinema>> getCinemas() {
        return new ResponseEntity<>(
                service.getCinemas(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Cinema> addCinema(@RequestBody Cinema cinema) {
        return new ResponseEntity<>(
                service.addCinema(cinema), HttpStatus.OK
        );
    }

    @DeleteMapping("{cinemaId}")
    public ResponseEntity<String> deleteCinema(@PathVariable("cinemaId") Long id) {
        Cinema cinema = service.getCinemaById(id);

        if(cinema == null)
            return new ResponseEntity<>("Cinema with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.deleteCinema(id), HttpStatus.OK);
    }

    @PutMapping("{cinemaId}")
    public ResponseEntity<String> updateCinema(@PathVariable("cinemaId") Long id,
                               @RequestParam String name,
                               @RequestParam String address,
                               @RequestParam List<CinemaHall> cinemaHalls) {

        return new ResponseEntity<>(service.updateCinema(id, name, address, cinemaHalls), HttpStatus.OK);
    }

    @PatchMapping("{cinemaId}")
    public ResponseEntity<String> patchCinema(@PathVariable("cinemaId") Long id,
                                              @RequestParam String name,
                                              @RequestParam String address,
                                              @RequestParam List<CinemaHall> cinemaHalls) {

        Cinema cinema = service.getCinemaById(id);

        if(cinema == null)
            return new ResponseEntity<>("Cinema with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.patchCinema(id, name, address, cinemaHalls), HttpStatus.OK);
    }

    @GetMapping("/{cinemaId}/movies")
    public ResponseEntity<List<Movie>> getMovies(@PathVariable("cinemaId") Long id) {
        return new ResponseEntity<>(service.getMovies(id), HttpStatus.OK);
    }
}
