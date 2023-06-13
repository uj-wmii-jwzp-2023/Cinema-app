package uj.wmii.jwzp.Cinemaapp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reservations")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reservation {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    private Seat seat;
    @OneToOne
    private Screening screening;


    public Reservation() {

    }

    public Reservation(User user, Seat seat, Screening screening) {
        this.user = user;
        this.seat = seat;
        this.screening = screening;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return user.equals(that.user) && seat.equals(that.seat) && screening.equals(that.screening);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, seat, screening);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", seat=" + seat +
                ", screening=" + screening +
                '}';
    }
}
