package io.github.kwisatzx.klgtask.repositories;

import io.github.kwisatzx.klgtask.model.Person;
import io.github.kwisatzx.klgtask.model.reservation.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Long> {

    @Query("SELECT reservation FROM Reservation reservation WHERE reservation.renter.id = :personId")
    List<Reservation> getAllReservations(Long personId);
}
