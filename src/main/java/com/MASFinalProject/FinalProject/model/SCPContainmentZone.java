package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SCPContainmentZone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long SCPContainmentZoneId;
    @NotNull
    @Enumerated
    private ContainmentZoneType zoneType;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "containedIn")
    private Set<SCP> containedSCP = new HashSet<>();
    public SCPContainmentZone(){

    }
    public SCPContainmentZone(@NotNull ContainmentZoneType zoneType) {
        this.zoneType = zoneType;
    }

    public ContainmentZoneType getZoneType() {
        return zoneType;
    }

    public void setZoneType(@NotNull ContainmentZoneType zoneType) {
        this.zoneType = zoneType;
    }

    public Set<SCP> getContainedSCP() {
        return containedSCP;
    }

    public void setContainedSCP(Set<SCP> containedSCP) {
        this.containedSCP = containedSCP;
    }
}
