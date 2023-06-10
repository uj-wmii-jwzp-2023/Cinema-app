package uj.wmii.jwzp.Cinemaapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "movies")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Duration duration;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String directors;

    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "screening_id")
    )
    private List<Screening> screenings;

    public Movie(){}

    public Movie(String title, Duration duration, String description, String directors) {
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.directors = directors;
        this.screenings = new ArrayList<>();
    }

    public Movie(String title, Duration duration, String description, String directors, List<Screening> screenings) {
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void addScreening(Screening newScreening) {
        if (!screenings.contains(newScreening)) {
            screenings.add(newScreening);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) && Objects.equals(duration, movie.duration) && Objects.equals(description, movie.description) && Objects.equals(directors, movie.directors) && Objects.equals(screenings, movie.screenings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, duration, description, directors, screenings);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", directors='" + directors + '\'' +
                ", screeningIds=" + screenings.stream().map(Screening::getId).toList() +
                '}';
    }
}
