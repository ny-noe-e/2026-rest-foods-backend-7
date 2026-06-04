package ch.noseryoung.restaurantbackend.repository;

import ch.noseryoung.restaurantbackend.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
