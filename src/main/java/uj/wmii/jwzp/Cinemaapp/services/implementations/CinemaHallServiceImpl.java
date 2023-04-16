package uj.wmii.jwzp.Cinemaapp.services.implementations;

import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaHallService;

import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    @Override
    public List<CinemaHall> getCinemaHalls() {
        return null;
    }

    @Override
    public CinemaHall addCinemaHall(CinemaHall cinemaHall) {
        return null;
    }

    @Override
    public String deleteCinemaHall(Long id) {
        return null;
    }

    @Override
    public String updateCinemaHall(Long id, Cinema cinema, List<Seat> seats) {
        return null;
    }

    @Override
    public String patchCinemaHall(Long id, Cinema cinema, List<Seat> seats) {
        return null;
    }
}
