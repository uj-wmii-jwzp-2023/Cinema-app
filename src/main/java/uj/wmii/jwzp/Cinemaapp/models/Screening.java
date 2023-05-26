package uj.wmii.jwzp.Cinemaapp.models;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return Objects.equals(name, screening.name) && Objects.equals(hall, screening.hall) && Objects.equals(movies, screening.movies) && Objects.equals(startTime, screening.startTime) && Objects.equals(endTime, screening.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hall, movies, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Screening{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hall=" + hall +
                ", movies=" + movies +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}