package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.Availability;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Seat;

import java.util.List;

public interface SeatService {

    Seat getSeatById(Long id);

    List<Seat> getSeats();

    Seat addSeat(Seat seat);

    String deleteSeat(Long id);

    String updateSeat(Long id, CinemaHall cinemaHall, Availability availability);

    String patchSeat(Long id, CinemaHall cinemaHall, Availability availability);
}
