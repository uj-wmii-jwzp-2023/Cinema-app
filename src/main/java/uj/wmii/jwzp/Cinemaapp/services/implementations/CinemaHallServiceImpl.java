package uj.wmii.jwzp.Cinemaapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.repositories.CinemaHallRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaHallService;

import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallRepository repository;
    @Autowired
    public CinemaHallServiceImpl(CinemaHallRepository cinemaHallRepository) {
        this.repository = cinemaHallRepository;
    }

    @Override
    public List<CinemaHall> getCinemaHalls() {
        return repository.findAll();
    }

    @Override
    public CinemaHall addCinemaHall(CinemaHall cinemaHall) {
        return repository.save(cinemaHall);
    }

    @Override
    public String deleteCinemaHall(Long id) {
        if(!repository.existsById(id)) {
            throw new IllegalStateException("CinemaHall with id " + id + " does not exist");
        }

        repository.deleteById(id);
        return "CinemaHall with id " + id + " was deleted";
    }

    @Override
    public String updateCinemaHall(Long id, Cinema cinema, List<Seat> seats) {
        CinemaHall cinemaHall = repository.findById(id).orElse(null);
        if(cinemaHall == null) {
            cinemaHall = new CinemaHall(cinema,seats);

            addCinemaHall(cinemaHall);
            return "CinemaHall created";
        }

        String result = "";

        if(!cinema.equals(cinemaHall.getCinema())) {
            cinemaHall.setCinema(cinema);
            result += "Cinema changed\n";
        }

        if(!seats.equals(cinemaHall.getSeats())) {
            cinemaHall.setSeats(seats);
            result += "Seats changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }

    @Override
    public String patchCinemaHall(Long id, Cinema cinema, List<Seat> seats) {
        CinemaHall cinemaHall = repository.findById(id).orElseThrow(
                () -> new IllegalStateException("CinemaHall with id " + id + " does not exist")
        );

        String result = "";

        if(!cinema.equals(cinemaHall.getCinema())) {
            cinemaHall.setCinema(cinema);
            result += "Cinema changed\n";
        }

        if(!seats.equals(cinemaHall.getSeats())) {
            cinemaHall.setSeats(seats);
            result += "Seats changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }
}
