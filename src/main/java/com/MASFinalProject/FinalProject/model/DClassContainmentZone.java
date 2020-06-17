package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DClassContainmentZone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long DClassContainmentZoneId;

    @NotBlank
    private String name;
    @NotBlank
    private String location;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "containedIn")
    private Set<DClass> containedDClass= new HashSet<>();
    public DClassContainmentZone(){

    }

    public DClassContainmentZone(@NotBlank String name, @NotBlank String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank String location) {
        this.location = location;
    }

    public Set<DClass> getContainedDClass() {
        return containedDClass;
    }

    public void setContainedDClass(Set<DClass> containedDClass) {
        this.containedDClass = containedDClass;
    }
}
