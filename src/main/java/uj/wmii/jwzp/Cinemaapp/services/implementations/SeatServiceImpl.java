package uj.wmii.jwzp.Cinemaapp.services.implementations;

import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Availability;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.SeatService;

import java.util.List;

@Service
public class SeatServiceImpl implements SeatService {
    @Override
    public List<Seat> getSeats() {
        return null;
    }

    @Override
    public Seat addSeat(Seat seat) {
        return null;
    }

    @Override
    public String deleteSeat(Long id) {
        return null;
    }

    @Override
    public String updateSeat(Long id, Availability availability) {
        return null;
    }

    @Override
    public String patchSeat(Long id, Availability availability) {
        return null;
    }
}
