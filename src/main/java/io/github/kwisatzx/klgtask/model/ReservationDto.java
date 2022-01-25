package io.github.kwisatzx.klgtask.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationDto {
    private RentalPropertyDto property;
    private String renterName;
    private String startDate;
    private String endDate;
    private Double monthlyCost;
}
