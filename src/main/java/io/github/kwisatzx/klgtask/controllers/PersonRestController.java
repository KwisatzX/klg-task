package io.github.kwisatzx.klgtask.controllers;

import io.github.kwisatzx.klgtask.model.Person;
import io.github.kwisatzx.klgtask.model.Reservation;
import io.github.kwisatzx.klgtask.repositories.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonRestController {

    PersonRepository personRepository;

    public PersonRestController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/person/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @GetMapping("/person/{id}/reservations")
    public List<Reservation> getReservationsForPerson(@PathVariable Long id) {
        return personRepository.getAllReservations(id);
    }
}
