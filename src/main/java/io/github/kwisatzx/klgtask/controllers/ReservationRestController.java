package io.github.kwisatzx.klgtask.controllers;

import io.github.kwisatzx.klgtask.model.Reservation;
import io.github.kwisatzx.klgtask.model.ReservationDto;
import io.github.kwisatzx.klgtask.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReservationRestController {

    private final ReservationRepository reservationRepository;
    private final ModelMapper modelMapper;

    public ReservationRestController(ReservationRepository reservationRepository, ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/reservations/{id}")
    public ReservationDto getById(@PathVariable Long id) {
        return reservationRepository.findById(id).map(this::toDto).orElse(null);
    }

    @GetMapping("/reservations")
    public List<ReservationDto> getReservationsForPerson(
            @RequestParam(required = false) String renter,
            @RequestParam(value = "property", required = false) Long propertyId) {
        if (renter != null) return reservationRepository.findByRenterName(renter).stream()
                .map(this::toDto).collect(Collectors.toList());
        if (propertyId != null) return reservationRepository.findByPropertyId(propertyId).stream()
                .map(this::toDto).collect(Collectors.toList());
        return null;
    }

    @PostMapping("/reservations/add")
    public ResponseEntity<Reservation> addNewReservation(@RequestBody ReservationDto reservationDto) {
        Reservation newReservation = toEntity(reservationDto);
        reservationRepository.save(newReservation);
        return ResponseEntity.ok(newReservation);
    }

    private ReservationDto toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationDto.class);
    }

    private Reservation toEntity(ReservationDto dto) {
        return modelMapper.map(dto, Reservation.class);
    }
}
