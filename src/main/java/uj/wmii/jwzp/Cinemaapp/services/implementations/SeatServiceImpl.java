package uj.wmii.jwzp.Cinemaapp.services.implementations;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Availability;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.repositories.SeatRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.SeatService;

import java.util.List;
import java.util.Optional;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository repository;
    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {
        this.repository = seatRepository;
    }


    public Seat getSeatById(Long id) {
        Optional<Seat> seatOptional = repository.findById(id);
        return seatOptional.orElse(null);
    }

    @Override
    public List<Seat> getSeats() {
        return repository.findAll();
    }

    @Override
    public Seat addSeat(Seat seat) {
        return repository.save(seat);
    }

    @Override
    public String deleteSeat(Long id) {
        if(!repository.existsById(id)) {
            throw new IllegalStateException("Seat with id " + id + " does not exist");
        }

        repository.deleteById(id);
        return "Seat with id " + id + " was deleted";
    }

    @Transactional
    public String updateSeat(Long id, Availability availability) {
        Seat seat = repository.findById(id).orElse(null);
        if(seat == null) {
            seat = new Seat(availability);

            addSeat(seat);
            return "Seat created";
        }

        String result = "";

        if(!availability.equals(seat.getAvailability())) {
            seat.setAvailability(availability);
            result += "Availability changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }

    @Transactional
    public String patchSeat(Long id, Availability availability) {
        Seat seat = repository.findById(id).orElseThrow(
                () -> new IllegalStateException("Seat with id " + id + " does not exist")
        );

        String result = "";

        if(!availability.equals(seat.getAvailability())) {
            seat.setAvailability(availability);
            result += "Availability changed\n";
        }

        if(result.length() == 0) {
            return "Nothing changed";
        } else
            return result;
    }
}
