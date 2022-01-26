package io.github.kwisatzx.klgtask.controllers;

import io.github.kwisatzx.klgtask.model.reservation.Reservation;
import io.github.kwisatzx.klgtask.model.reservation.ReservationGetDto;
import io.github.kwisatzx.klgtask.model.reservation.ReservationPostDto;
import io.github.kwisatzx.klgtask.model.reservation.ReservationPostDtoValidator;
import io.github.kwisatzx.klgtask.repositories.PersonRepository;
import io.github.kwisatzx.klgtask.repositories.RentalPropertyRepository;
import io.github.kwisatzx.klgtask.repositories.ReservationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ReservationRestController {

    private final ReservationRepository reservationRepository;
    private final PersonRepository personRepository;
    private final RentalPropertyRepository propertyRepository;
    private final ModelMapper modelMapper;

    public ReservationRestController(ReservationRepository reservationRepository,
                                     PersonRepository personRepository,
                                     RentalPropertyRepository propertyRepository,
                                     ModelMapper modelMapper) {
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(
                new ReservationPostDtoValidator(reservationRepository, personRepository, propertyRepository));
    }

    @GetMapping("/reservations/{id}")
    public ReservationGetDto getById(@PathVariable Long id) {
        return reservationRepository.findById(id).map(this::toDto).orElse(null);
    }

    @GetMapping("/reservations")
    public List<ReservationGetDto> getReservationsForPerson(
            @RequestParam(required = false) String renter,
            @RequestParam(value = "property", required = false) Long propertyId) {
        if (renter != null) return reservationRepository.findByRenterName(renter).stream()
                .map(this::toDto).collect(Collectors.toList());
        if (propertyId != null) return reservationRepository.findByPropertyId(propertyId).stream()
                .map(this::toDto).collect(Collectors.toList());
        return null;
    }

    @PostMapping("/reservations/add")
    public ResponseEntity<ReservationPostDto> addNewReservation(
            @RequestBody @Validated ReservationPostDto reservationPostDto,
            BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().header("Errors", getErrorsString(result)).build();
        }
        reservationRepository.save(ToEntity(reservationPostDto));
        return ResponseEntity.ok(reservationPostDto);
    }

    @Transactional
    @PutMapping("/reservations/{id}/edit")
    public ResponseEntity<ReservationPostDto> editReservation(
            @PathVariable Long id,
            @RequestBody ReservationPostDto reservationPostDto) {
        Optional<Reservation> toUpdate = reservationRepository.findById(id);
        if (!toUpdate.isPresent()) return ResponseEntity.badRequest().build();
        else {
            Reservation res = toUpdate.get();
            BeanUtils.copyProperties(reservationPostDto, res);
            return ResponseEntity.ok().body(reservationPostDto);
        }
    }

    private String getErrorsString(BindingResult result) {
        return result.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", "));
    }

    private ReservationGetDto toDto(Reservation reservation) {
        return modelMapper.map(reservation, ReservationGetDto.class);
    }

    private Reservation ToEntity(ReservationPostDto dto) {
        Reservation reservation = modelMapper.map(dto, Reservation.class);
        reservation.setProperty(propertyRepository.findById(dto.getPropertyId()).get());
        reservation.setRenter(personRepository.findById(dto.getRenterId()).get());
        return reservation;
    }
}
