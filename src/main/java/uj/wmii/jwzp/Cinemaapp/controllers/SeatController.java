package uj.wmii.jwzp.Cinemaapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping
    public List<Seat> getSeats() {
        return service.getSeats();
    }

    @PostMapping
    public Seat addSeat(@RequestBody Seat seat) {
        return service.addSeat(seat);
    }

    @DeleteMapping("{seatId}")
    public String deleteSeat(@PathVariable("seatId") Long id) {
        return service.deleteSeat(id);
    }

    @PutMapping("{seatId}")
    public String updateSeat(@PathVariable("seatId") Long id,
                             @RequestParam Availability availability) {
        return service.updateSeat(id, availability);
    }

    @PatchMapping("{seatId}")
    public String patchSeat(@PathVariable("seatId") Long id,
                                  @RequestParam(required = false)  Availability availability) {
        return service.patchSeat(id, availability);
    }
}
