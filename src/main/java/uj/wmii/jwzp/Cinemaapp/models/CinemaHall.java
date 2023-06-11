package uj.wmii.jwzp.Cinemaapp.models;

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

    public CinemaHall(Cinema cinema,List<Screening> screenings, List<Seat> seats) {
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

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addScreening(Screening newScreening) {
        this.screenings.add(newScreening);
    }

    public void addSeat(Seat newSeat) {
        this.seats.add(newSeat);
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
        return "CinemaHall{" +
                "id=" + id +
                ", cinema=" + cinema +
                ", screenings=" + screenings +
                ", seats=" + seats +
                '}';
    }
}
