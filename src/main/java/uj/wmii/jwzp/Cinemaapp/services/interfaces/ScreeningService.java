package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;

import java.time.Instant;
import java.util.List;

public interface ScreeningService {

    Screening getScreeningById(Long id);

    List<Screening> getScreenings();

    Screening addScreening(Screening screening);

    String deleteScreening(Long id);

    String updateScreening(Long id, String name, CinemaHall hall, List<Movie> movies, Instant startTime, Instant endTime);

    String patchScreening(Long id, String name, CinemaHall hall, List<Movie> movies, Instant startTime, Instant endTime);
}
