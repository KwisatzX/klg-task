package io.github.kwisatzx.klgtask.repositories;

import io.github.kwisatzx.klgtask.model.RentalProperty;
import io.github.kwisatzx.klgtask.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalPropertyRepository extends CrudRepository<RentalProperty, Long> {

    @Query("SELECT reservation FROM Reservation reservation WHERE reservation.property.id = :propertyId")
    List<Reservation> getAllReservations(Long propertyId);
}
