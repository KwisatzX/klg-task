package io.github.kwisatzx.klgtask.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rental_properties")
public class RentalProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "property_id")
    private Long id;
    @Column(name = "property_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Person owner;
    @Column(name = "unit_price")
    private Double unitPrice;
    @Column(name = "surface_area")
    private Double surfaceArea;
    private String description;
    @OneToMany(mappedBy = "property", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        if (reservations == null) reservations = new ArrayList<>();
        return Collections.unmodifiableList(reservations);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalProperty that = (RentalProperty) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }
}
