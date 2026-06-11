package ch.noseryoung.restaurantbackend.controller;

import ch.noseryoung.restaurantbackend.config.JwtService;
import ch.noseryoung.restaurantbackend.model.Reservation;
import ch.noseryoung.restaurantbackend.service.LoginService;
import ch.noseryoung.restaurantbackend.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final JwtService jwtService;
    private final LoginService loginService;
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader
    ) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        if (loginService.doesUserExist(username)){
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservations());
        }else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }
    }

    @GetMapping("/NP") // this gets not private information without a jwt aka reservation date and table
    public ResponseEntity<List<Reservation>> getAllReservationsNotPrivate() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getAllReservations());
    }

    @GetMapping("/{reservationId}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long reservationId) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getReservationById(reservationId));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservation));
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId, @Valid @RequestBody Reservation reservation) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.updateReservation(reservationId, reservation));
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
