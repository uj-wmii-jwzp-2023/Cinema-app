package uj.wmii.jwzp.Cinemaapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private Availability availability;
    @ManyToOne
    private CinemaHall cinemaHall;

    public Seat() {
        this.availability = Availability.FREE;
    }

    public Seat(CinemaHall cinemaHall, Availability availability) {
        this.cinemaHall = cinemaHall;
        this.availability = availability;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    @JsonIgnore
    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }
}
