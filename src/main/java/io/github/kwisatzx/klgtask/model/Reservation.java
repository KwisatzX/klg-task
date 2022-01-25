package io.github.kwisatzx.klgtask.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private RentalProperty property;
    @ManyToOne
    @JoinColumn(name = "renter_id")
    private Person renter;
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "dd-mm-yyyy", fallbackPatterns = {"yyyy-mm-dd"})
    private LocalDate startDate;
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "dd-mm-yyyy", fallbackPatterns = {"yyyy-mm-dd"})
    private LocalDate endDate;
    @Column(name = "monthly_cost")
    private Double monthlyCost;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservation that = (Reservation) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
