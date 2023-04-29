package uj.wmii.jwzp.Cinemaapp.services.implementations;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Movie;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.repositories.MovieRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.MovieService;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository repository;
    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.repository = movieRepository;
    }


    public Movie getMovieById(Long id) {
        Optional<Movie> movieOptional = repository.findById(id);
        return movieOptional.orElse(null);
    }

    @Override
    public List<Movie> getMovies() {
        return repository.findAll();
    }

    @Override
    public Movie addMovie(Movie movie) {
        return repository.save(movie);
    }

    @Override
    public String deleteMovie(Long id) {

        repository.deleteById(id);
        return "Movie with id " + id + " was deleted";
    }

    @Transactional
    public String updateMovie(Long id, String name, Duration duration, String description, String directors, List<Screening> screenings) {
        Movie movie = repository.findById(id).orElse(null);
        if(movie == null) {
            movie = new Movie(name,duration,description,directors,screenings);

            addMovie(movie);
            return "Movie created";
        }

        String result = "";

        if(!name.equals(movie.getName())) {
            movie.setName(name);
            result += "Name changed\n";
        }

        if(!duration.equals(movie.getDuration())) {
            movie.setDuration(duration);
            result += "Duration changed\n";
        }

        if(!description.equals(movie.getDescription())) {
            movie.setDescription(description);
            result += "Description changed\n";
        }

        if(!directors.equals(movie.getDirectors())) {
            movie.setDirectors(directors);
            result += "Directors changed\n";
        }

        if(!screenings.equals(movie.getScreenings())) {
            movie.setScreenings(screenings);
            result += "Screenings changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }

    @Transactional
    public String patchMovie(Long id, String name, Duration duration, String description, String directors, List<Screening> screenings) {
        Movie movie = getMovieById(id);

        String result = "";

        if(!name.equals(movie.getName())) {
            movie.setName(name);
            result += "Name changed\n";
        }

        if(!duration.equals(movie.getDuration())) {
            movie.setDuration(duration);
            result += "Duration changed\n";
        }

        if(!description.equals(movie.getDescription())) {
            movie.setDescription(description);
            result += "Description changed\n";
        }

        if(!directors.equals(movie.getDirectors())) {
            movie.setDirectors(directors);
            result += "Directors changed\n";
        }

        if(!screenings.equals(movie.getScreenings())) {
            movie.setScreenings(screenings);
            result += "Screenings changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }
}
