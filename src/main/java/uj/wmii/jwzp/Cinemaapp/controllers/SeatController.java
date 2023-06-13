package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.DataTransferObjects.SeatDTO;
import uj.wmii.jwzp.Cinemaapp.models.Availability;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaHallService;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@RestController
@RequestMapping("/seats")
public class SeatController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeatController.class);

    private final SeatService seatService;
    private final CinemaHallService cinemaHallService;

    @Autowired
    public SeatController(SeatService seatService, CinemaHallService cinemaHallService) {
        this.seatService = seatService;
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("seatId") Long id) {
        LOGGER.debug("Getting seat by id: {}", id);

        Seat seat = seatService.getSeatById(id);

        if (seat == null) {
            LOGGER.info("Seat with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Found seat with id {}: {}", id, seat);
        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seat>> getSeats() {
        LOGGER.debug("Getting all seats");

        List<Seat> seats = seatService.getSeats();

        LOGGER.info("Found {} seats", seats.size());
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat) {
        LOGGER.debug("Adding seat: {}", seat);

        Seat addedSeat = seatService.addSeat(seat);
        seat.getCinemaHall().addSeat(seat);

        LOGGER.info("Added seat with id {}: {}", addedSeat.getId(), addedSeat);
        return new ResponseEntity<>(addedSeat, HttpStatus.OK);
    }

    @PostMapping("/dto")
    public ResponseEntity<Seat> addSeat(@RequestBody SeatDTO seatDTO) {
        Long cinemaHallId = seatDTO.getCinemaHallId();
        CinemaHall cinemaHall = cinemaHallService.getCinemaHallById(cinemaHallId);

        if (cinemaHall == null) {
            LOGGER.info("CinemaHall with id {} not found", cinemaHallId);
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Seat newSeat = new Seat(cinemaHall, seatDTO.getAvailability());

        cinemaHall.addSeat(newSeat);

        seatService.addSeat(newSeat);

        return new ResponseEntity<>(newSeat, HttpStatus.OK);
    }

    @DeleteMapping("{seatId}")
    public ResponseEntity<String> deleteSeat(@PathVariable("seatId") Long id) {
        LOGGER.debug("Deleting seat with id: {}", id);

        Seat seat = seatService.getSeatById(id);

        if (seat == null) {
            LOGGER.info("Seat with id {} not found", id);
            return new ResponseEntity<>("Seat with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String deletedSeat = seatService.deleteSeat(id);
        LOGGER.info("Deleted seat with id {}: {}", id, deletedSeat);
        return new ResponseEntity<>(deletedSeat, HttpStatus.OK);
    }

    @PutMapping("{seatId}")
    public ResponseEntity<String> updateSeat(@PathVariable("seatId") Long id,
                                             @RequestParam CinemaHall cinemaHall,
                                             @RequestParam Availability availability) {
        LOGGER.debug("Updating seat with id: {}", id);

        String updatedSeat = seatService.updateSeat(id, cinemaHall, availability);

        LOGGER.info("Updated seat with id {}: {}", id, updatedSeat);
        return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
    }

    @PatchMapping("{seatId}")
    public ResponseEntity<String> patchSeat(@PathVariable("seatId") Long id,
                                            @RequestParam(required = false) CinemaHall cinemaHall,
                                            @RequestParam(required = false) Availability availability) {
        LOGGER.debug("Patching seat with id: {}", id);

        Seat seat = seatService.getSeatById(id);

        if (seat == null) {
            LOGGER.info("Seat with id {} not found", id);
            return new ResponseEntity<>("Seat with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        }

        String patchedSeat = seatService.patchSeat(id, cinemaHall, availability);
        LOGGER.info("Patched seat with id {}: {}", id, patchedSeat);
        return new ResponseEntity<>(patchedSeat, HttpStatus.OK);
    }
}
