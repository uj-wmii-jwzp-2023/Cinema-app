package uj.wmii.jwzp.Cinemaapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.wmii.jwzp.Cinemaapp.models.CinemaHall;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {
}
