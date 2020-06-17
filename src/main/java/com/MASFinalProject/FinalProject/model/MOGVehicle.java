package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MOGVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long MOGVehicleId;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String color;
    @DecimalMin(value = "0.0")
    private float weight;

    @ManyToOne
    @JoinColumn(name="MOGTeamId",nullable = true)
    private MOGTeam assignedTeam;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "assignedVehicles")
    @Size(min=1,max=3)
    private Set<Technician> assignedTechnicians= new HashSet<>();
    public MOGVehicle() {
    }

    public MOGVehicle(@NotBlank String name, @NotBlank String description, @NotBlank String color, @DecimalMin(value = "0.0") float weight) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(@NotBlank String color) {
        this.color = color;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(@DecimalMin(value = "0.0") float weight) {
        this.weight = weight;
    }

    public MOGTeam getAssignedTeam() {
        return assignedTeam;
    }

    public void setAssignedTeam(MOGTeam assignedTeam) {
        this.assignedTeam = assignedTeam;
    }


    public Set<Technician> getAssignedTechnicians() {
        return assignedTechnicians;
    }

    public void setAssignedTechnicians(Set<Technician> assignedTechnicians) {
        this.assignedTechnicians = assignedTechnicians;
    }
}
