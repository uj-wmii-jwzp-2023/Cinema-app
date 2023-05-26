package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
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


    @GetMapping("/{cinemaHallId}")
    public ResponseEntity<CinemaHall> getCinemaHallById(@PathVariable("cinemaHallId") Long id) {
        CinemaHall cinemaHall = service.getCinemaHallById(id);

        if(cinemaHall == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(cinemaHall, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CinemaHall>> getCinemaHalls() {
        return new ResponseEntity<>(
                service.getCinemaHalls(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<CinemaHall> addCinemaHall(@RequestBody CinemaHall cinemaHall) {
        return new ResponseEntity<>(
                service.addCinemaHall(cinemaHall), HttpStatus.OK
        );
    }

    @DeleteMapping("{cinemaHallId}")
    public ResponseEntity<String> deleteCinemaHall(@PathVariable("cinemaHallId") Long id) {
        CinemaHall cinemaHall = service.getCinemaHallById(id);

        if(cinemaHall == null)
            return new ResponseEntity<>("Cinema hall with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.deleteCinemaHall(id), HttpStatus.OK);
    }

    @PutMapping("{cinemaHallId}")
    public ResponseEntity<String> updateCinemaHall(@PathVariable("cinemaHallId") Long id,
                               @RequestParam Cinema cinema,
                               @RequestParam List<Screening> screenings,
                               @RequestParam List<Seat> seats) {

        return new ResponseEntity<>(service.updateCinemaHall(id, cinema, screenings, seats), HttpStatus.OK);
    }

    @PatchMapping("{cinemaHallId}")
    public ResponseEntity<String> patchCinemaHall(@PathVariable("cinemaHallId") Long id,
                                  @RequestParam(required = false) Cinema cinema,
                                  @RequestParam(required = false) List<Screening> screenings,
                                  @RequestParam(required = false) List<Seat> seats) {
        CinemaHall cinemaHall = service.getCinemaHallById(id);

        if(cinemaHall == null)
            return new ResponseEntity<>("Cinema hall with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.patchCinemaHall(id, cinema, screenings, seats), HttpStatus.OK);
    }
}
