package io.github.kwisatzx.klgtask.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RentalProperty getProperty() {
        return property;
    }

    public void setProperty(RentalProperty property) {
        this.property = property;
    }

    public Person getRenter() {
        return renter;
    }

    public void setRenter(Person renter) {
        this.renter = renter;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Double cost) {
        this.monthlyCost = cost;
    }

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
