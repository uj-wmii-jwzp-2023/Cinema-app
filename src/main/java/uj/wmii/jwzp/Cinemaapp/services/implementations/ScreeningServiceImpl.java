package uj.wmii.jwzp.Cinemaapp.services.implementations;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.repositories.ScreeningRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.ScreeningService;

import java.time.Instant;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    private final ScreeningRepository repository;
    @Autowired
    public ScreeningServiceImpl(ScreeningRepository screeningRepository) {
        this.repository = screeningRepository;
    }
    @Override
    public List<Screening> getScreenings() {
        return repository.findAll();
    }

    @Override
    public Screening addScreening(Screening screening) {
        return repository.save(screening);
    }

    @Override
    public String deleteScreening(Long id) {
        if(!repository.existsById(id)) {
            throw new IllegalStateException("Screening with id " + id + " does not exist");
        }

        repository.deleteById(id);
        return "Screening with id " + id + " was deleted";
    }

    @Transactional
    public String updateScreening(Long id, String name, CinemaHall hall, List<Movie> movies, Instant startTime, Instant endTime) {
        Screening screening = repository.findById(id).orElse(null);
        if(screening == null) {
            screening = new Screening(name,hall,movies,startTime,endTime);

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
    public String patchScreening(Long id, String name, CinemaHall hall, List<Movie> movies, Instant startTime, Instant endTime) {
        Screening screening = repository.findById(id).orElseThrow(
                () -> new IllegalStateException("Screening with id " + id + " does not exist")
        );

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
}
