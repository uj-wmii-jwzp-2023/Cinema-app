package uj.wmii.jwzp.Cinemaapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.repositories.ScreeningRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.ScreeningService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    private final ScreeningRepository repository;
    @Autowired
    public ScreeningServiceImpl(ScreeningRepository screeningRepository) {
        this.repository = screeningRepository;
    }


    public Screening getScreeningById(Long id) {
        Optional<Screening> screeningOptional = repository.findById(id);
        return screeningOptional.orElse(null);
    }

    @Override
    public List<Screening> getScreenings() {
        return repository.findAll();
    }

    @Override
    public Screening addScreening(Screening screening) {
//        if(checkCollisions(screening.getId(),screening.getHall(),screening.getStartTime(),screening.getEndTime())) {
//            return null;
//        }
        return repository.save(screening);
    }

    @Override
    public String deleteScreening(Long id) {

        repository.deleteById(id);
        return "Screening with id " + id + " was deleted";
    }

    @Transactional
    public String updateScreening(Long id, String name, CinemaHall hall, List<Movie> movies, LocalDateTime startTime, LocalDateTime endTime, BigDecimal ticketPrice) {
        if(checkCollisions(id,hall,startTime,endTime)) {
            return null;
        }
        Screening screening = repository.findById(id).orElse(null);
        if(screening == null) {
            screening = new Screening(name,hall,movies,startTime,endTime,ticketPrice);

            addScreening(screening);
            return "Screening created";
        }

        String result = "";

        if(!name.equals(screening.getName())) {
            screening.setName(name);
            result += "Name changed\n";
        }

        if(!hall.equals(screening.getHall())) {
            screening.setHall(hall);
            result += "Hall changed\n";
        }

        if(!movies.equals(screening.getMovies())) {
            screening.setMovies(movies);
            result += "Movies changed\n";
        }

        if(!startTime.equals(screening.getStartTime())) {
            screening.setStartTime(startTime);
            result += "StartTime changed\n";
        }

        if(!endTime.equals(screening.getEndTime())) {
            screening.setEndTime(endTime);
            result += "EndTime changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }

    @Transactional
    public String patchScreening(Long id, String name, CinemaHall hall, List<Movie> movies, LocalDateTime startTime, LocalDateTime endTime, BigDecimal ticketPrice) {
        if(checkCollisions(id,hall,startTime,endTime)) {
            return null;
        }
        Screening screening = getScreeningById(id);

        String result = "";

        if(!name.equals(screening.getName())) {
            screening.setName(name);
            result += "Name changed\n";
        }

        if(!hall.equals(screening.getHall())) {
            screening.setHall(hall);
            result += "Hall changed\n";
        }

        if(!movies.equals(screening.getMovies())) {
            screening.setMovies(movies);
            result += "Movies changed\n";
        }

        if(!startTime.equals(screening.getStartTime())) {
            screening.setStartTime(startTime);
            result += "StartTime changed\n";
        }

        if(!endTime.equals(screening.getEndTime())) {
            screening.setEndTime(endTime);
            result += "EndTime changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }

    public boolean checkCollisions(Long id, CinemaHall hall, LocalDateTime startTime, LocalDateTime endTime) {
        var screenings = repository.findAll()
                .stream().filter(s -> s.getHall().equals(hall))
                .filter(s -> !s.getId().equals(id))
                .toList();
        for(var s : screenings) {
            if(s.getStartTime().isBefore(endTime) && s.getEndTime().isBefore(startTime)) {
                return true;
            }
        }
        return false;
    }
}
