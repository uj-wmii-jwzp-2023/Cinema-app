package uj.wmii.jwzp.Cinemaapp.models;

import jakarta.persistence.*;

@Entity
public class Screening {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
