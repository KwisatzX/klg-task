package io.github.kwisatzx.klgtask.controllers;

import io.github.kwisatzx.klgtask.model.RentalPropertyDto;
import io.github.kwisatzx.klgtask.model.ReservationDto;
import io.github.kwisatzx.klgtask.repositories.RentalPropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RentalPropertyRestController {

    private final RentalPropertyRepository repository;
    private final ModelMapper modelMapper;

    public RentalPropertyRestController(RentalPropertyRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/property/{id}")
    public RentalPropertyDto getById(@PathVariable Long id) {
        return repository.findById(id).map(property -> toDto(property, RentalPropertyDto.class)).orElse(null);
    }

    @GetMapping("/property/{id}/reservations")
    public List<ReservationDto> getReservationsForProperty(@PathVariable Long id) {
        return repository.getAllReservations(id).stream()
                .map(reservation -> toDto(reservation, ReservationDto.class)).collect(Collectors.toList());
    }

    private <T> T toDto(Object obj, Class<T> clazz) {
        return modelMapper.map(obj, clazz);
    }
}
