package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.DataTransferObjects.CinemaHallDTO;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaHallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaService;

import java.util.List;

@RestController
@RequestMapping("/cinemaHalls")
public class CinemaHallController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CinemaHallController.class);

    private final CinemaHallService cinemaHallService;
    private final CinemaService cinemaService;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService, CinemaService cinemaService) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaService = cinemaService;
    }

    @GetMapping("/{cinemaHallId}")
    public ResponseEntity<CinemaHall> getCinemaHallById(@PathVariable("cinemaHallId") Long id) {
        LOGGER.debug("Getting cinema hall by id: {}", id);

        CinemaHall cinemaHall = cinemaHallService.getCinemaHallById(id);

        if (cinemaHall == null) {
            LOGGER.info("Cinema hall with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Found cinema hall with id {}: {}", id, cinemaHall);
        return new ResponseEntity<>(cinemaHall, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CinemaHall>> getCinemaHalls() {
        LOGGER.debug("Getting all cinema halls");

        List<CinemaHall> cinemaHalls = cinemaHallService.getCinemaHalls();

        LOGGER.info("Found {} cinema halls", cinemaHalls.size());
        return new ResponseEntity<>(cinemaHalls, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CinemaHall> addCinemaHall(@RequestBody CinemaHall cinemaHall) {
        LOGGER.debug("Adding cinema hall: {}", cinemaHall);

        CinemaHall addedCinemaHall = cinemaHallService.addCinemaHall(cinemaHall);

        LOGGER.info("Added cinema hall with id {}: {}", addedCinemaHall.getId(), addedCinemaHall);
        return new ResponseEntity<>(addedCinemaHall, HttpStatus.OK);
    }

    @PostMapping("/dto")
    public ResponseEntity<CinemaHall> addCinemaHall(@RequestBody CinemaHallDTO cinemaHallDTO) {
        Long cinemaId = cinemaHallDTO.getCinemaId();
        Cinema cinema = cinemaService.getCinemaById(cinemaId);

        if (cinema == null) {
            LOGGER.info("CinemaHall with id {} not found", cinemaId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        CinemaHall newCinemaHall = new CinemaHall(cinema);
        cinema.addCinemaHall(newCinemaHall);

        cinemaHallService.addCinemaHall(newCinemaHall);

        return new ResponseEntity<>(newCinemaHall, HttpStatus.OK);
    }

    @DeleteMapping("{cinemaHallId}")
    public ResponseEntity<String> deleteCinemaHall(@PathVariable("cinemaHallId") Long id) {
        LOGGER.debug("Deleting cinema hall with id: {}", id);

        CinemaHall cinemaHall = cinemaHallService.getCinemaHallById(id);

        if (cinemaHall == null) {
            LOGGER.info("Cinema hall with id {} not found", id);
            return new ResponseEntity<>("Cinema hall with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String deletedCinemaHall = cinemaHallService.deleteCinemaHall(id);
        LOGGER.info("Deleted cinema hall with id {}: {}", id, deletedCinemaHall);
        return new ResponseEntity<>(deletedCinemaHall, HttpStatus.OK);
    }

    @PutMapping("{cinemaHallId}")
    public ResponseEntity<String> updateCinemaHall(@PathVariable("cinemaHallId") Long id,
                                                   @RequestParam Cinema cinema,
                                                   @RequestParam List<Screening> screenings,
                                                   @RequestParam List<Seat> seats) {
        LOGGER.debug("Updating cinema hall with id: {}", id);

        String updatedCinemaHall = cinemaHallService.updateCinemaHall(id, cinema, screenings, seats);

        LOGGER.info("Updated cinema hall with id {}: {}", id, updatedCinemaHall);
        return new ResponseEntity<>(updatedCinemaHall, HttpStatus.OK);
    }

    @PatchMapping("{cinemaHallId}")
    public ResponseEntity<String> patchCinemaHall(@PathVariable("cinemaHallId") Long id,
                                                  @RequestParam(required = false) Cinema cinema,
                                                  @RequestParam(required = false) List<Screening> screenings,
                                                  @RequestParam(required = false) List<Seat> seats) {
        LOGGER.debug("Patching cinema hall with id: {}", id);

        CinemaHall cinemaHall = cinemaHallService.getCinemaHallById(id);

        if (cinemaHall == null) {
            LOGGER.info("Cinema hall with id {} not found", id);
            return new ResponseEntity<>("Cinema hall with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String patchedCinemaHall = cinemaHallService.patchCinemaHall(id, cinema, screenings, seats);
        LOGGER.info("Patched cinema hall with id {}: {}", id, patchedCinemaHall);
        return new ResponseEntity<>(patchedCinemaHall, HttpStatus.OK);
    }
}

