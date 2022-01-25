package io.github.kwisatzx.klgtask.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kwisatzx.klgtask.model.reservation.Reservation;
import io.github.kwisatzx.klgtask.model.reservation.ReservationPostDto;
import io.github.kwisatzx.klgtask.repositories.PersonRepository;
import io.github.kwisatzx.klgtask.repositories.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationRestControllerTest {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    ModelMapper modelMapper;

    @Test
    public void mappingToDtoIsCorrect() throws JsonProcessingException {
        Reservation reservation = reservationRepository.findById(2L).get();
        ReservationPostDto result = modelMapper.map(reservation, ReservationPostDto.class);

        System.out.println(
                "new ObjectMapper().writeValueAsString(testObj) = " + new ObjectMapper().writeValueAsString(result));
    }
}