package uj.wmii.jwzp.Cinemaapp.models;

import jakarta.persistence.*;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Duration duration;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String directors;
    @Column(nullable = false)
    @ManyToMany
    private List<Screening> screenings;

    public Movie(){}

    public Movie(String name, Duration duration, String description, String directors, List<Screening> screenings) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.directors = directors;
        this.screenings = screenings;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return name.equals(movie.name) && description.equals(movie.description) && directors.equals(movie.directors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, directors);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", directors='" + directors + '\'' +
                '}';
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }
}
