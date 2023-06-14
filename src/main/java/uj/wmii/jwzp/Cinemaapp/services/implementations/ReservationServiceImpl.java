package uj.wmii.jwzp.Cinemaapp.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uj.wmii.jwzp.Cinemaapp.models.Reservation;
import uj.wmii.jwzp.Cinemaapp.models.Screening;
import uj.wmii.jwzp.Cinemaapp.models.Seat;
import uj.wmii.jwzp.Cinemaapp.models.User;
import uj.wmii.jwzp.Cinemaapp.repositories.ReservationRepository;
import uj.wmii.jwzp.Cinemaapp.services.interfaces.ReservationService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository repository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Reservation getReservationById(Long id) {
        var reservation = repository.findById(id);
        return reservation.orElse(null);
    }

    @Override
    public List<Reservation> getReservations() {
        return repository.findAll();
    }

    @Override
    public List<Reservation> getReservationsByUserId(Long id) {
        return repository.findAll()
                .stream()
                .filter(reservation -> reservation.getUser().getId().equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation addReservation(Reservation reservation) {
        var user = reservation.getUser();
        user.setAccountBalance(user.getAccountBalance().subtract(reservation.getPrice()));
        return repository.save(reservation);
    }

    @Override
    public String deleteReservation(Long id) {
        var reservation = getReservationById(id);
        if(reservation != null) {
            var user = reservation.getUser();
            user.setAccountBalance(user.getAccountBalance().add(reservation.getPrice()));
        }
        repository.deleteById(id);
        return "User account with id " + id + " was deleted";
    }

    @Override
    public String updateReservation(Long id, User user, Seat seat, Screening screening, BigDecimal price) {
        Reservation reservation = getReservationById(id);

        if(reservation == null) {
            Reservation newReservation = new Reservation(user,seat,screening,price);

            addReservation(newReservation);
            return "Reservation created";
        }

        String result = "";

        if(!reservation.getUser().equals(user)) {
            reservation.setUser(user);
            result += "User Changed";
        }

        if(!reservation.getSeat().equals(seat)) {
            reservation.setSeat(seat);
            result += "Seat Changed";
        }

        if(!reservation.getScreening().equals(screening)) {
            reservation.setScreening(screening);
            result += "Screening Changed";
        }

        if(!reservation.getPrice().equals(price)) {
            reservation.setPrice(price);
            result += "Price Changed";
        }

        if (result.length() == 0)
            return "Nothing changed";
        else
            return result;
    }

    @Override
    public String patchReservation(Long id, User user, Seat seat, Screening screening, BigDecimal price) {
        Reservation reservation = getReservationById(id);

        if(reservation == null) {
            return "Reservation with id " + id + " does not exist";
        }

        String result = "";

        if(!reservation.getUser().equals(user)) {
            reservation.setUser(user);
            result += "User Changed";
        }

        if(!reservation.getSeat().equals(seat)) {
            reservation.setSeat(seat);
            result += "Seat Changed";
        }

        if(!reservation.getScreening().equals(screening)) {
            reservation.setScreening(screening);
            result += "Screening Changed";
        }

        if(!reservation.getPrice().equals(price)) {
            reservation.setPrice(price);
            result += "Price Changed";
        }

        if (result.length() == 0)
            return "Nothing changed";
        else
            return result;
    }
}
