package io.github.kwisatzx.klgtask.model.reservation;

import io.github.kwisatzx.klgtask.model.Person;
import io.github.kwisatzx.klgtask.model.RentalProperty;
import io.github.kwisatzx.klgtask.repositories.PersonRepository;
import io.github.kwisatzx.klgtask.repositories.RentalPropertyRepository;
import io.github.kwisatzx.klgtask.repositories.ReservationRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

public class ReservationPostDtoValidator implements Validator {

    private final ReservationRepository reservationRepository;
    private final PersonRepository personRepository;
    private final RentalPropertyRepository propertyRepository;

    public ReservationPostDtoValidator(ReservationRepository reservationRepository,
                                       PersonRepository personRepository,
                                       RentalPropertyRepository propertyRepository) {
        this.reservationRepository = reservationRepository;
        this.personRepository = personRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ReservationPostDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ReservationPostDto dto = (ReservationPostDto) obj;
        if (dto.getPropertyId() == null || dto.getRenterId() == null || dto.getStartDate() == null) err.reject("");

        Optional<RentalProperty> property = propertyRepository.findById(dto.getPropertyId());
        if (!property.isPresent()) err.rejectValue("propertyId", "", "Property not found");
        Optional<Person> renter = personRepository.findById(dto.getRenterId());
        if (!renter.isPresent()) err.rejectValue("renterId", "", "Person not found");

        if (property.isPresent()) {
            RentalProperty propertyObj = property.get();
            if (dto.getMonthlyCost() == null || dto.getMonthlyCost() == 0.0) {
                dto.setMonthlyCost(propertyObj.getUnitPrice() * propertyObj.getSurfaceArea());
            }

            List<Reservation> propertyReservations = reservationRepository.findByPropertyId(propertyObj.getId());
            for (Reservation res : propertyReservations) {
                if (dto.getStartDate().isAfter(res.getStartDate()) && dto.getStartDate().isBefore(res.getEndDate()))
                    err.rejectValue("startDate", "", "Wrong start date");
                if (dto.getEndDate().isAfter(res.getStartDate()) && dto.getStartDate().isBefore(res.getStartDate()))
                    err.rejectValue("endDate", "", "Wrong end date");
                if (dto.getStartDate().equals(res.getStartDate()))
                    err.rejectValue("startDate", "", "Wrong start date");
                if (dto.getEndDate().equals(res.getEndDate()))
                    err.rejectValue("endDate", "", "Wrong end date");
            }
        }
    }
}
