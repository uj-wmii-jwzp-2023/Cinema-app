package uj.wmii.jwzp.Cinemaapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "screenings")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private CinemaHall hall;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startTime;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endTime;
    @Column(nullable = false)
    private BigDecimal ticketPrice;
    @JsonManagedReference
    @ManyToMany(mappedBy = "screenings")
    private List<Movie> movies;


    public Screening() { }
    public Screening(String name, CinemaHall hall, List<Movie> movies, LocalDateTime startTime, LocalDateTime endTime) {
        this.name = name;
        this.hall = hall;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movies = movies;
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

    public BigDecimal getTicketPrice() { return ticketPrice; }

    public void setTicketPrice(BigDecimal ticketPrice) { this.ticketPrice = ticketPrice; }

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
        String result = "Screening{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", hallId=" + (hall != null ? hall.getId() : null);

        if (movies != null)
            result += ", movieIds=" + movies.stream().map(Movie::getId).toList();

        result += ", startTime=" + startTime +
                  ", endTime=" + endTime +
                  '}';
        return result;
    }
}