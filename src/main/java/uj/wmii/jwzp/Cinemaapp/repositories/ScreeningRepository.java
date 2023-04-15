package uj.wmii.jwzp.Cinemaapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uj.wmii.jwzp.Cinemaapp.models.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long> {
}
