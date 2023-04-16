package uj.wmii.jwzp.Cinemaapp.services.implementations;

import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.ScreeningService;

import java.time.Instant;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    @Override
    public List<Screening> getScreenings() {
        return null;
    }

    @Override
    public Screening addScreening(Screening screening) {
        return null;
    }

    @Override
    public String deleteScreening(Long id) {
        return null;
    }

    @Override
    public String updateScreening(Long id, String name, CinemaHall hall, List<Movie> movies, Instant startTime, Instant endTime) {
        return null;
    }

    @Override
    public String patchScreening(Long id, String name, CinemaHall hall, List<Movie> movies, Instant startTime, Instant endTime) {
        return null;
    }
}
