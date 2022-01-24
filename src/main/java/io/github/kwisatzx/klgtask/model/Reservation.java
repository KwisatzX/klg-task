package io.github.kwisatzx.klgtask.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;
    @ManyToOne
    private RentalProperty property;
    @ManyToOne
    private Person renter;
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate startDate;
    @Column(name = "end_date")
    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate endDate;
    private Double cost;

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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
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
