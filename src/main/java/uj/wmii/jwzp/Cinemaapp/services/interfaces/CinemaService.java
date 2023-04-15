package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;

import java.util.List;

public interface CinemaService {
    List<Cinema> getCinemas();

    Cinema addCinema(Cinema cinema);

    String deleteCinema(Long id);

    String updateCinema(Long id, String name, String address, List<CinemaHall> cinemaHalls);

    String patchCinema(Long id, String name, String address);
}
