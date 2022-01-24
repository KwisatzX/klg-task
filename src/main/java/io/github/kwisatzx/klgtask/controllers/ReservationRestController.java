package io.github.kwisatzx.klgtask.controllers;

import io.github.kwisatzx.klgtask.model.Reservation;
import io.github.kwisatzx.klgtask.repositories.ReservationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationRestController {

    ReservationRepository reservationRepository;

    public ReservationRestController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservations/{id}")
    public Reservation getById(@PathVariable Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservationsForPerson(
            @RequestParam(required = false) String renter,
            @RequestParam(value = "property", required = false) Long propertyId) {
        if (renter != null) return reservationRepository.findByRenterName(renter);
        if (propertyId != null) return reservationRepository.findByPropertyId(propertyId);
        return null;
    }

    @PostMapping("/reservations/add")
    public ResponseEntity<Reservation> addNewReservation(@RequestBody Reservation newReservation) {
        reservationRepository.save(newReservation);
        return ResponseEntity.ok(newReservation);
    }
}
