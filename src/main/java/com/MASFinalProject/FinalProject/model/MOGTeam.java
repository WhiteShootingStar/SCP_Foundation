package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class MOGTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long MOGTeamId;
    @NotBlank
    private String callName;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "hisTeam")
    @Size(min = 1)
    private Set<MOGMember> team= new HashSet<>();

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "assignedTeam")
    private Set<MOGVehicle> vehicles= new HashSet<>();
    public MOGTeam() {

    }

    public MOGTeam(@NotBlank String callName) {
        this.callName = callName;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName(@NotBlank String callName) {
        this.callName = callName;
    }

    public Set<MOGMember> getTeam() {
        return team;
    }

    public void setTeam(Set<MOGMember> team) {
        this.team = team;
    }

    public Set<MOGVehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(Set<MOGVehicle> vehicles) {
        this.vehicles = vehicles;
    }


}
