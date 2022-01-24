package io.github.kwisatzx.klgtask.controllers;

import io.github.kwisatzx.klgtask.model.RentalProperty;
import io.github.kwisatzx.klgtask.model.Reservation;
import io.github.kwisatzx.klgtask.repositories.RentalPropertyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalPropertyController {

    RentalPropertyRepository repository;

    public RentalPropertyController(RentalPropertyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/property/{id}")
    public RentalProperty getById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/property/{id}/reservations")
    public List<Reservation> getReservationsForProperty(@PathVariable Long id) {
        return repository.getAllReservations(id);
    }
}
