package uj.wmii.jwzp.Cinemaapp.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    @ManyToMany(mappedBy = "screenings")
    private List<Movie> movies;


    public Screening() { }
    public Screening(String name, CinemaHall hall, List<Movie> movies, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movies = movies;
        assignScreeningToMovies(movies);
        hall.addScreening(this);
    }

    public void assignScreeningToMovies(List<Movie> movies) {
        for (Movie movie : movies) {
            movie.addScreening(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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