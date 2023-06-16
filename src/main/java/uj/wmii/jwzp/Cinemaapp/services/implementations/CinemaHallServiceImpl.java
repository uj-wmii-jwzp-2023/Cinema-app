package uj.wmii.jwzp.Cinemaapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.repositories.CinemaHallRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.CinemaHallService;

import java.util.List;
import java.util.Optional;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallRepository repository;
    @Autowired
    public CinemaHallServiceImpl(CinemaHallRepository cinemaHallRepository) {
        this.repository = cinemaHallRepository;
    }


    @Override
    public CinemaHall getCinemaHallById(Long id) {
        Optional<CinemaHall> cinemaOptional = repository.findById(id);
        return cinemaOptional.orElse(null);
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
        repository.deleteById(id);

        return "CinemaHall with id " + id + " was deleted";
    }

    @Override
    public String updateCinemaHall(Long id, Cinema cinema, List<Screening> screenings, List<Seat> seats) {
        CinemaHall cinemaHall = repository.findById(id).orElse(null);
        if(cinemaHall == null) {
            cinemaHall = new CinemaHall(cinema,screenings,seats);

            addCinemaHall(cinemaHall);
            return "CinemaHall created";
        }

        String result = "";

        if(!cinema.equals(cinemaHall.getCinema())) {
            cinemaHall.setCinema(cinema);
            result += "Cinema changed\n";
        }

        if(!screenings.equals(cinemaHall.getScreenings())) {
            cinemaHall.setScreenings(screenings);
            result += "Screenings changed\n";
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
    public String patchCinemaHall(Long id, Cinema cinema, List<Screening> screenings, List<Seat> seats) {
        CinemaHall cinemaHall = getCinemaHallById(id);

        String result = "";

        if(cinema != null && !cinema.equals(cinemaHall.getCinema())) {
            cinemaHall.setCinema(cinema);
            result += "Cinema changed\n";
        }

        if(screenings != null && !screenings.equals(cinemaHall.getScreenings())) {
            cinemaHall.setScreenings(screenings);
            result += "Screenings changed\n";
        }

        if(seats != null && !seats.equals(cinemaHall.getSeats())) {
            cinemaHall.setSeats(seats);
            result += "Seats changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }
}
