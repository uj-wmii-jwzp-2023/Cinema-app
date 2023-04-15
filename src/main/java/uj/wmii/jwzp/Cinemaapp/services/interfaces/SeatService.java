package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.Availability;
import uj.wmii.jwzp.Cinemaapp.models.Seat;

import java.util.List;

public interface SeatService {
    List<Seat> getSeats();

    Seat addSeat(Seat seat);

    String deleteSeat(Long id);

    String updateSeat(Long id, Availability availability);

    String patchSeat(Long id, Availability availability);
}
