package io.github.kwisatzx.klgtask.model.reservation;

import io.github.kwisatzx.klgtask.model.RentalPropertyDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationGetDto {
    private RentalPropertyDto property;
    private String renterName;
    private String startDate;
    private String endDate;
    private Double monthlyCost;
}
