package uj.wmii.jwzp.Cinemaapp.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cinemas")
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column(nullable = false)
    @OneToMany
    private List<CinemaHall> cinemaHalls;


    public Cinema() {
    }

    public Cinema(String name, String address, List<CinemaHall> cinemaHalls) {
        this.name = name;
        this.address = address;
        this.cinemaHalls = cinemaHalls;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<CinemaHall> getCinemaHalls() {
        return cinemaHalls;
    }

    public void setCinemaHalls(List<CinemaHall> cinemaHalls) {
        this.cinemaHalls = cinemaHalls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(name, cinema.name) && Objects.equals(address, cinema.address) && Objects.equals(cinemaHalls, cinema.cinemaHalls);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, cinemaHalls);
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", cinemaHalls=" + cinemaHalls +
                '}';
    }
}
