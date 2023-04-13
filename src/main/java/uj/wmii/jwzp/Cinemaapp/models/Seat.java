package uj.wmii.jwzp.Cinemaapp.models;

import jakarta.persistence.*;

enum Availability {
FREE, TAKEN
}

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private Availability availability;


    public Seat() {
        this.availability = Availability.FREE;
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
}
