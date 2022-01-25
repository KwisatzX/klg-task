package io.github.kwisatzx.klgtask.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalPropertyDto {
    private Long id;
    private String name;
    private String ownerName;
    private Double unitPrice;
    private Double surfaceArea;
    private String description;
}
