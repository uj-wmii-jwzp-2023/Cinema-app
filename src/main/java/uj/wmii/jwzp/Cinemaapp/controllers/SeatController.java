package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uj.wmii.jwzp.Cinemaapp.models.Availability;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.SeatService;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {
    private final SeatService service;

    @Autowired
    public SeatController(SeatService seatService) {
        service = seatService;
    }


    @GetMapping("/{seatId}")
    public ResponseEntity<Seat> getSeatById(@PathVariable("seatId") Long id) {
        Seat seat = service.getSeatById(id);

        if(seat == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(seat, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seat>> getSeats() {
        return new ResponseEntity<>(
                service.getSeats(), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Seat> addSeat(@RequestBody Seat seat) {
        return new ResponseEntity<>(
                service.addSeat(seat), HttpStatus.OK
        );
    }

    @DeleteMapping("{seatId}")
    public ResponseEntity<String> deleteSeat(@PathVariable("seatId") Long id) {
        Seat seat = service.getSeatById(id);

        if(seat == null)
            return new ResponseEntity<>("Seat with id " + id + " does not exist", HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.deleteSeat(id), HttpStatus.OK);
    }

    @PutMapping("{seatId}")
    public ResponseEntity<String> updateSeat(@PathVariable("seatId") Long id,
                             @RequestParam Availability availability) {
        
        return new ResponseEntity<>(service.updateSeat(id, availability), HttpStatus.OK);
    }

    @PatchMapping("{seatId}")
    public ResponseEntity<String> patchSeat(@PathVariable("seatId") Long id,
                                  @RequestParam(required = false)  Availability availability) {

        Seat seat = service.getSeatById(id);

        if(seat == null)
            return new ResponseEntity<>("Seat with id " + id + " does not exist", HttpStatus.NOT_FOUND);
        
        return new ResponseEntity<>(service.patchSeat(id, availability), HttpStatus.OK);
    }
}
