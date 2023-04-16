package uj.wmii.jwzp.Cinemaapp.services.implementations;

import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.MovieService;

import java.time.Duration;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public List<Movie> getMovies() {
        return null;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return null;
    }

    @Override
    public String deleteMovie(Long id) {
        return null;
    }

    @Override
    public String updateMovie(Long id, String name, Duration duration, String description, String directors, List<Screening> screenings) {
        return null;
    }

    @Override
    public String patchMovie(Long id, String name, Duration duration, String description, String directors, List<Screening> screenings) {
        return null;
    }
}
