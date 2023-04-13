package uj.wmii.jwzp.Cinemaapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uj.wmii.jwzp.Cinemaapp.models.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Override
    Optional<Employee> findById(Long aLong);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<Employee> findEmployeeByEmail(String email);
}
