package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SCP {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long SCPId;
    @Min(1)
    @Column(unique = true)
    private int number;
    @Enumerated(EnumType.STRING)
    @NotNull
    private SCPType scpType;
    @NotBlank
    private String info;

    @Transient
    private boolean isConducted=false;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy ="observedSCP" )
    private Set<Scientist> observedBy = new HashSet<>();
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "conductedSCP")
    private Set<Scientist> conductedExperimentsOnBy = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "SCPContainmentZoneId",nullable = true)
    private SCPContainmentZone containedIn;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy = "object")
    private Set<Experiment> objectIn= new HashSet<>();
    public SCP() {
    }

    public SCP(@Min(1) int number, @NotNull SCPType scpType, @NotBlank String info) {
        this.number = number;
        this.scpType = scpType;
        this.info = info;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(@Min(1) int number) {
        this.number = number;
    }

    public SCPType getScpType() {
        return scpType;
    }

    public void setScpType(@NotNull SCPType scpType) {
        this.scpType = scpType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(@NotBlank String info) {
        this.info = info;
    }

    public Set<Scientist> getObservedBy() {
        return observedBy;
    }

    public void setObservedBy(Set<Scientist> observedBy) {
        this.observedBy = observedBy;
    }

    public Set<Scientist> getConductedExperimentsOnBy() {
        return conductedExperimentsOnBy;
    }

    public void setConductedExperimentsOnBy(Set<Scientist> conductedExperimentsOnBy) {
        this.conductedExperimentsOnBy = conductedExperimentsOnBy;
    }

    public SCPContainmentZone getContainedIn() {
        return containedIn;
    }

    public void setContainedIn(SCPContainmentZone containedIn) {
        this.containedIn = containedIn;
    }

    public Set<Experiment> getObjectIn() {
        return objectIn;
    }

    public void setObjectIn(Set<Experiment> objectIn) {
        this.objectIn = objectIn;
    }

    public boolean isConducted() {
        return isConducted;
    }

    public void setConducted(boolean conducted) {
        isConducted = conducted;
    }


    public long getSCPId() {
        return SCPId;
    }

    public void addToObservedBy(Scientist scientist) {
        if (scientist == null) {
            throw new IllegalArgumentException("Experiment is null");
        }
        if (!this.observedBy.contains(scientist)) {
            this.observedBy.add(scientist);
            scientist.addToObserved(this);
        }
    }

    public void addToConductors(Scientist scientist) {
        if (scientist == null) {
            throw new IllegalArgumentException("Experiment is null");
        }
        if (!this.conductedExperimentsOnBy.contains(scientist)) {
            addToObservedBy(scientist);
            isConducted=true;
            this.conductedExperimentsOnBy.add(scientist);
            scientist.addToConducted(this);
            scientist.addToObserved(this);
        }
    }

    public void removeFromObservedBy(Scientist scientist) {
        if (scientist == null) {
            throw new IllegalArgumentException("Experiment is null");
        }
        if (this.observedBy.contains(scientist)) {
            this.observedBy.remove(scientist);
            scientist.removeFromObserved(this);
            removeFromConductors(scientist, false);
        }

    }

    public void removeFromConductors(Scientist scientist, boolean removeFromMainLink) {
        if (scientist == null) {
            throw new IllegalArgumentException("Experiment is null");
        }
        if (this.conductedExperimentsOnBy.contains(scientist)) {
            this.conductedExperimentsOnBy.remove(scientist);
            scientist.removeFromConducted(this, removeFromMainLink);
            if (removeFromMainLink) {
                removeFromObservedBy(scientist);
            }else{
                isConducted=false;
            }
        }

    }



    public boolean isThisScientistConducts(Scientist scientist){
        return observedBy.contains(scientist)&&conductedExperimentsOnBy.contains(scientist);
    }

    @Override
    public String toString() {
        return "SCP{" +
                "number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SCP)) return false;

        SCP scp = (SCP) o;

        if (SCPId != scp.SCPId) return false;
        return number == scp.number;
    }

    @Override
    public int hashCode() {
        int result = (int) (SCPId ^ (SCPId >>> 32));
        result = 31 * result + number;
        return result;
    }
}
