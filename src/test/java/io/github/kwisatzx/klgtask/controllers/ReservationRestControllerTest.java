package io.github.kwisatzx.klgtask.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.kwisatzx.klgtask.model.Person;
import io.github.kwisatzx.klgtask.model.PersonDto;
import io.github.kwisatzx.klgtask.model.Reservation;
import io.github.kwisatzx.klgtask.model.ReservationDto;
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
        Reservation reservation = reservationRepository.findById(1L).get();
        ReservationDto result = modelMapper.map(reservation, ReservationDto.class);

        System.out.println(
                "new ObjectMapper().writeValueAsString(testObj) = " + new ObjectMapper().writeValueAsString(result));

        Person person = personRepository.findById(1L).get();
        PersonDto result2 = modelMapper.map(person, PersonDto.class);

        System.out.println(new ObjectMapper().writeValueAsString(result2));
    }
}