package io.github.kwisatzx.klgtask.model;

import io.github.kwisatzx.klgtask.model.reservation.ReservationGetDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PersonDto {
    private String name;
    private List<ReservationGetDto> reservations;

    public PersonDto() {
        reservations = new ArrayList<>();
    }
}
