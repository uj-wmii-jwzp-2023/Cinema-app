package uj.wmii.jwzp.Cinemaapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cinema_halls")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @OneToMany(mappedBy = "hall")
    private List<Screening> screenings;
    @OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;


    public CinemaHall() { }

    public CinemaHall(Cinema cinema) {
        this.cinema = cinema;
        this.screenings = new ArrayList<>();
        this.seats = new ArrayList<>();
    }

    public CinemaHall(Cinema cinema, List<Screening> screenings, List<Seat> seats) {
        this.cinema = cinema;
        this.screenings = screenings;
        this.seats = seats;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    @JsonIgnore
    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public List<Seat> getSeats() { return seats; }

    @JsonIgnore
    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addScreening(Screening newScreening) {
        this.screenings.add(newScreening);
    }

    public void addSeat(Seat newSeat) {
        this.seats.add(newSeat);
    }

    @JsonIgnore
    public Integer getNumOfFreeSeats() {
        Integer numOfFreeSeats = 0;

        for (Seat seat : seats)
            if (seat.getAvailability() == Availability.FREE)
                numOfFreeSeats++;

        return numOfFreeSeats;
    }

    public void releaseAllSeats() {
        for (Seat seat : seats)
            if (seat.getAvailability() != Availability.FREE)
                seat.setAvailability(Availability.FREE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CinemaHall that = (CinemaHall) o;
        return Objects.equals(cinema, that.cinema) && Objects.equals(screenings, that.screenings) && Objects.equals(seats, that.seats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cinema, screenings, seats);
    }

    @Override
    public String toString() {
        String result = "CinemaHall{" +
                "id=" + id +
                ", cinemaId=" + (cinema != null ? cinema.getId() : null);

        if (screenings != null)
            result += ", screeningsIds=" + screenings.stream().map(Screening::getId).toList();

        if (seats != null)
            result += ", seatsIds=" + seats.stream().map(Seat::getId).toList() + '}';
        else
            result += '}';

        return result;
    }
}
