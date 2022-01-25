package io.github.kwisatzx.klgtask.controllers;

import io.github.kwisatzx.klgtask.model.PersonDto;
import io.github.kwisatzx.klgtask.model.reservation.ReservationGetDto;
import io.github.kwisatzx.klgtask.repositories.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PersonRestController {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    public PersonRestController(PersonRepository personRepository, ModelMapper modelMapper) {
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/person/{id}")
    public PersonDto getPersonById(@PathVariable Long id) {
        return personRepository.findById(id).map(person -> toDto(person, PersonDto.class)).orElse(null);
    }

    @GetMapping("/person/{id}/reservations")
    public List<ReservationGetDto> getReservationsForPerson(@PathVariable Long id) {
        return personRepository.getAllReservations(id).stream()
                .map(reservation -> toDto(reservation, ReservationGetDto.class)).collect(Collectors.toList());
    }

    private <T> T toDto(Object obj, Class<T> clazz) {
        return modelMapper.map(obj, clazz);
    }
}
