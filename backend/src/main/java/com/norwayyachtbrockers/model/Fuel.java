package com.norwayyachtbrockers.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.norwayyachtbrockers.dto.request.FuelRequestDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Table(name = "fuel_types", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@JsonPropertyOrder({ "fuel_type_id", "fuel_type_name"})
@Getter
@Setter
public class Fuel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("fuel_type_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 40, unique = true)
    @JsonProperty("fuel_type_name")
    private String name;

    @OneToMany(mappedBy = "fuelType",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JsonIgnore
    private Set<YachtModel> yachtModels;

    public Fuel() {
    }

    public Fuel(String name) {
        this.name = name;
    }

    // Convenience method to add a YachtModel to the Fuel type
    public void addYachtModel(YachtModel yachtModel) {
        yachtModels.add(yachtModel);
        yachtModel.setFuelType(this);
    }

    // Convenience method to remove a YachtModel from the Fuel type
    public void removeYachtModel(YachtModel yachtModel) {
        yachtModels.remove(yachtModel);
        if (yachtModel.getFuelType() == this) {
            yachtModel.setFuelType(null);
        }
    }

}
