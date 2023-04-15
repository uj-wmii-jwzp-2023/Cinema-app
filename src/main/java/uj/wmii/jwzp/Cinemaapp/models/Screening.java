package uj.wmii.jwzp.Cinemaapp.models;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "screenings")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @OneToOne
    @PrimaryKeyJoinColumn
    private CinemaHall hall;
    @Column(nullable = false)
    @ManyToMany
    private List<Movie> movies;
    @Column(nullable = false)
    private Instant startTime;
    @Column(nullable = false)
    private Instant endTime;


    public Screening() { }
    public Screening(String name, CinemaHall hall, List<Movie> movies, Instant startTime, Instant endTime) {
        this.name = name;
        this.hall = hall;
        this.movies = movies;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CinemaHall getHall() {
        return hall;
    }

    public void setHall(CinemaHall hall) {
        this.hall = hall;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}