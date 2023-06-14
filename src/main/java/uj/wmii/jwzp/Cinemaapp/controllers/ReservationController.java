package uj.wmii.jwzp.Cinemaapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Reservation;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.ReservationService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        service = reservationService;
    }
    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("reservationId") Long id) {
        LOGGER.debug("Getting reservation by id: {}", id);

        Reservation reservation = service.getReservationById(id);

        if(reservation == null) {
            LOGGER.info("Reservation with id {} not found",id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        LOGGER.info("Found reservation with id {}: {}",id, reservation);
        return ResponseEntity.ok(reservation);
    }
    @GetMapping
    public ResponseEntity<List<Reservation>> getReservations() {
        LOGGER.debug("Getting all reservations");

        List<Reservation> reservations = service.getReservations();

        LOGGER.info("Found {} reservations",reservations.size());
        return ResponseEntity.ok(reservations);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reservation>> getReservationsByUser(@PathVariable("userId") Long id) {
        LOGGER.debug("Getting all reservations of user with id: {}",id);

        List<Reservation> reservations = service.getReservationsByUserId(id);

        LOGGER.info("Found {} reservations for user with id: {}",reservations.size(),id);
        return ResponseEntity.ok(reservations);
    }
    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        LOGGER.debug("Adding reservation: {}",reservation);

        Reservation addedReservation = service.addReservation(reservation);

        LOGGER.info("Added Reservation with id {}: {}",addedReservation.getId(),addedReservation);
        return ResponseEntity.ok(reservation);
    }
    @DeleteMapping("{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable("reservationId") Long id) {
        LOGGER.debug("Deleting reservation with id: {}",id);

        Reservation reservation = service.getReservationById(id);

        if(reservation == null) {
            LOGGER.info("Reservation with id {} not found",id);
            return new ResponseEntity<>("Reservation with id " + id + " does not exit",HttpStatus.NOT_FOUND);
        }

        String deletedReservation = service.deleteReservation(id);
        LOGGER.info("Deleted reservation with id {}: {}",id,deletedReservation);
        return ResponseEntity.ok(deletedReservation);
    }
    @PutMapping("{reservationId}")
    public ResponseEntity<String> updateReservation(@PathVariable("reservationId") Long id,
                                                    @RequestParam User user,
                                                    @RequestParam Seat seat,
                                                    @RequestParam Screening screening,
                                                    @RequestParam BigDecimal price) {
        LOGGER.debug("Updating reservation with id: {}",id);

        String updatedReservation = service.updateReservation(id,user,seat,screening,price);

        LOGGER.info("Updated reservation with id {}: {}",id,updatedReservation);
        return ResponseEntity.ok(updatedReservation);
    }
    @PatchMapping("{reservationId}")
    public ResponseEntity<String> patchReservation(@PathVariable("reservationId") Long id,
                                                   @RequestParam User user,
                                                   @RequestParam Seat seat,
                                                   @RequestParam Screening screening,
                                                   @RequestParam BigDecimal price) {
        LOGGER.debug("Patching reservation with id: {}",id);

        Reservation reservation = service.getReservationById(id);

        if(reservation == null) {
            LOGGER.info("Reservation with id {} not found",id);
            return new ResponseEntity<>("Reservation with id " + id + " does not exist",HttpStatus.NOT_FOUND);
        }

        String patchedReservation = service.patchReservation(id,user,seat,screening,price);

        LOGGER.info("Patched reservation with id {}: {}",id,patchedReservation);
        return ResponseEntity.ok(patchedReservation);
    }
}
