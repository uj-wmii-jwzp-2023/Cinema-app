package uj.wmii.jwzp.Cinemaapp.services;

import uj.wmii.jwzp.Cinemaapp.models.Cinema;

import java.util.List;

public interface CinemaService {
    List<Cinema> getCinemas();

    Cinema addCinema(Cinema cinema);

    String deleteCinema(Long id);

    String updateCinema(Long id, String name, String address);

    String patchCinema(Long id, String name, String address);
}
