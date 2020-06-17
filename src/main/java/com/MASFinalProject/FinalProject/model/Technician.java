package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "PersonId")
public class Technician extends Person {
    @NotBlank
    private String qualification;
    @Min(0)
    private int experience;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Size(min = 1)
    @JoinTable(
            name = "Vehicle_Driver",
            joinColumns = @JoinColumn(name = "PersonId"),
            inverseJoinColumns = @JoinColumn(name = "MOGVehicleId")
    )
    private Set<MOGVehicle> assignedVehicles = new HashSet<>();

    public Technician() {
    }

    public Technician(@NotBlank String name, @NotBlank String surname, @NotBlank String qualification, @Min(0) int experience) {
        super(name, surname);
        this.qualification = qualification;
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(@NotBlank String qualification) {
        this.qualification = qualification;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(@Min(0) int experience) {
        this.experience = experience;
    }

    public Set<MOGVehicle> getAssignedVehicles() {
        return assignedVehicles;
    }

    public void setAssignedVehicles(Set<MOGVehicle> assignedVehicles) {
        this.assignedVehicles = assignedVehicles;
    }
}
