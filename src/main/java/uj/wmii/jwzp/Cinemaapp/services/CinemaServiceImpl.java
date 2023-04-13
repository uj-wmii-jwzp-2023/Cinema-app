package uj.wmii.jwzp.Cinemaapp.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Cinema;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;
import uj.wmii.jwzp.Cinemaapp.repositories.CinemaRepository;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository repository;

    @Autowired
    public CinemaServiceImpl(CinemaRepository repository) {
        this.repository = repository;
    }

    boolean CorrectName(String name) {
        if (name != null &&
                name.length() > 0) {

            return true;
        }

        throw new IllegalStateException("Invalid name");
    }

    boolean CorrectAddress(String address) {
        if (address != null &&
                address.length() > 5) {

            return true;
        }

        throw new IllegalStateException("Invalid address");
    }

    @Override
    public List<Cinema> getCinemas() {
        return repository.findAll();
    }

    @Override
    public Cinema addCinema(Cinema cinema) {
        if (CorrectName(cinema.getName()) && CorrectAddress(cinema.getAddress()))
            return repository.save(cinema);
        else
            throw new IllegalStateException("Cannot create cinema");
    }

    @Override
    public String deleteCinema(Long id) {
        if(!repository.existsById(id)) {
            throw new IllegalStateException("Cinema with id " + id + " does not exist");
        }

        repository.deleteById(id);
        return "Cinema with id " + id + " was deleted";
    }

    @Transactional
    public String updateCinema(Long id, String name, String address, List<CinemaHall> cinemaHalls) {
        Cinema cinema = repository.findById(id).orElse(null);
        if(cinema == null) {
            Cinema newCinema = new Cinema(name,address, cinemaHalls);

            addCinema(newCinema);
            return "Cinema created";
        }

        String result = "";

        if(!name.equals(cinema.getName())) {
            cinema.setName(name);
            result += "Name changed\n";
        }

        if(!address.equals(cinema.getAddress())) {
            cinema.setAddress(address);
            result += "Address changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }

    @Transactional
    public String patchCinema(Long id, String name, String address) {
        Cinema cinema = repository.findById(id).orElseThrow(
                () -> new IllegalStateException("Cinema with id " + id + " does not exist")
        );

        String result = "";

        if(name != null && !name.equals(cinema.getName())) {
            cinema.setName(name);
            result += "Name changed\n";
        }

        if(address != null && !address.equals(cinema.getAddress())) {
            cinema.setAddress(address);
            result += "Address changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }
}
