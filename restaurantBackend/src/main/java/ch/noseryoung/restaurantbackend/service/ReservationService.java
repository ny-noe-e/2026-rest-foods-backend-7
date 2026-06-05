package ch.noseryoung.restaurantbackend.service;

import ch.noseryoung.restaurantbackend.model.Reservation;
import ch.noseryoung.restaurantbackend.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository repository;

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    public Reservation getReservationById(Long reservationId) {
        return repository.findById(reservationId).orElseThrow(() -> new EntityNotFoundException("Reservation with id: " + reservationId + " not found"));
    }

    public Reservation createReservation(Reservation reservation) {
        return repository.save(reservation);
    }

    public Reservation updateReservation(Long reservationId, Reservation reservationDetails) {
        if (repository.existsById(reservationId)) {
            reservationDetails.setId(reservationId);

            return repository.save(reservationDetails);
        } else {
            throw new EntityNotFoundException("Reservation with id: " + reservationId + " not found");
        }
    }

    public void deleteReservation(Long reservationId) {
        if (repository.existsById(reservationId)) {
            repository.deleteById(reservationId);
        } else {
            throw new EntityNotFoundException("Reservation with id: " + reservationId + " not found");
        }
    }
}