package io.github.kwisatzx.klgtask.model.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReservationPostDto {
    private Long propertyId;
    private Long renterId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double monthlyCost;
}
