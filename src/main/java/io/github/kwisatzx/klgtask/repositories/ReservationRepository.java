package io.github.kwisatzx.klgtask.repositories;

import io.github.kwisatzx.klgtask.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByRenterName(String name);

    List<Reservation> findByPropertyId(Long id);
}
