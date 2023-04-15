package uj.wmii.jwzp.Cinemaapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.wmii.jwzp.Cinemaapp.models.Seat;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
