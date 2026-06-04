package ch.noseryoung.restaurantbackend.controller;

import ch.noseryoung.restaurantbackend.model.Reservation;
import ch.noseryoung.restaurantbackend.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;





@RestController
@RequestMapping("/reservations")

public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {

        return ResponseEntity.
                status(HttpStatus.OK).
                body(reservationService.getAllReservations());
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long reservationId) {

        return ResponseEntity.
                status(HttpStatus.OK).
                body(reservationService.getReservationById(reservationId));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation) {
        return ResponseEntity.
                status(HttpStatus.CREATED).
                body(reservationService.createReservation(reservation));
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId, @Valid @RequestBody Reservation reservation) {
        return ResponseEntity.
                status(HttpStatus.OK).
                body(reservationService.updateReservation(reservationId,reservation));

    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.
                status(HttpStatus.NO_CONTENT).
                build();
    }




}
