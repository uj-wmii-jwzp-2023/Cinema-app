package uj.wmii.jwzp.Cinemaapp.services.interfaces;

import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;

import java.time.Duration;
import java.util.List;

public interface MovieService {
    List<Movie> getMovies();

    Movie addMovie(Movie movie);

    String deleteMovie(Long id);

    String updateMovie(Long id, String name, Duration duration, String description, String directors, List<Screening> screenings);

    String patchMovie(Long id, String name, Duration duration, String description, String directors, List<Screening> screenings);
}
