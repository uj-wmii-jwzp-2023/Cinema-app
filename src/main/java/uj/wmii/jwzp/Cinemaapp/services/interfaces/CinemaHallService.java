package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Seat;

import java.util.List;

public interface CinemaHallService {

    CinemaHall getCinemaHallById(Long id);

    List<CinemaHall> getCinemaHalls();

    CinemaHall addCinemaHall(CinemaHall cinemaHall);

    String deleteCinemaHall(Long id);

    String updateCinemaHall(Long id, Cinema cinema, List<Seat> seats);

    String patchCinemaHall(Long id, Cinema cinema, List<Seat> seats);
}
