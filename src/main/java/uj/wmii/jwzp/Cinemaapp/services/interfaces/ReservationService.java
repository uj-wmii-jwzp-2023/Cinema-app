package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.Reservation;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.models.User;

import java.math.BigDecimal;
import java.util.List;

public interface ReservationService {
    Reservation getReservationById(Long id);
    List<Reservation> getReservations();
    List<Reservation> getReservationsByUserId(Long id);
    Reservation addReservation(Reservation reservation);
    String deleteReservation(Long id);
    String updateReservation(Long id, User user, Seat seat, Screening screening, BigDecimal price);
    String patchReservation(Long id, User user, Seat seat, Screening screening, BigDecimal price);
}
