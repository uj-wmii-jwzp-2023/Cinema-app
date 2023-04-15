package uj.wmii.jwzp.Cinemaapp.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cinema_halls")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Cinema cinema;
    @Column(nullable = false)
    @OneToMany
    private List<Seat> seats;


    public CinemaHall() { }

    public CinemaHall(Cinema cinema, List<Seat> seats) {
        this.cinema = cinema;
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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
