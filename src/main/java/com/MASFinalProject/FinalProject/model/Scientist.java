package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "PersonId")
public class Scientist extends Person {

    @NotBlank
    private String title;
    @NotBlank
    private String field;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "Observers_SCP",
            joinColumns = @JoinColumn(name = "PersonId"),
            inverseJoinColumns = @JoinColumn(name = "SCPId")
    )
    private Set<SCP> observedSCP= new HashSet<>();
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "Conductors_SCP",
            joinColumns = @JoinColumn(name = "PersonId"),
            inverseJoinColumns = @JoinColumn(name = "SCPId")
    )
    private Set<SCP> conductedSCP= new HashSet<>();

    public Scientist(){

    }
    public Scientist(@NotBlank String name, @NotBlank String surname, @NotBlank String title, @NotBlank String field) {
        super(name, surname);
        this.title = title;
        this.field = field;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(@NotBlank String field) {
        this.field = field;
    }

    public Set<SCP> getObservedSCP() {
        return observedSCP;
    }

    public void setObservedSCP(Set<SCP> observedSCP) {
        this.observedSCP = observedSCP;
    }

    public Set<SCP> getConductedSCP() {
        return conductedSCP;
    }

    public void setConductedSCP(Set<SCP> conductedSCP) {
        this.conductedSCP = conductedSCP;
    }

    public void addToObserved(@NotNull SCP scp) {
        if (scp == null) {
            throw new IllegalArgumentException("SCP is null");
        }
        if (!this.observedSCP.contains(scp)) {
            this.observedSCP.add(scp);
            scp.addToObservedBy(this);
        }
    }

    public void addToConducted(SCP scp) {
        if (scp == null) {
            throw new IllegalArgumentException("SCP is null");
        }
        if (!this.conductedSCP.contains(scp) ) {
            addToObserved(scp);
            this.conductedSCP.add(scp);
            scp.addToConductors(this);
            scp.addToObservedBy(this);
        }
    }

    public void removeFromObserved(SCP scp) {
        if (scp == null) {
            throw new IllegalArgumentException("SCP is null");
        }
        if (this.observedSCP.contains(scp)) {
            this.observedSCP.remove(scp);
            scp.removeFromObservedBy(this);
            removeFromConducted(scp, false);
        }

    }

    public void removeFromConducted(SCP scp, boolean removeFromMainLink) {
        if (scp == null) {
            throw new IllegalArgumentException("SCP is null");
        }
        if (this.conductedSCP.contains(scp)) {
            this.conductedSCP.remove(scp);
            scp.removeFromConductors(this, removeFromMainLink);
            if (removeFromMainLink) {
                removeFromObserved(scp);
            }
        }

    }

    @Override
    public String toString() {
        return "Scientist{" +
                "title='" + title + '\'' +
                ", field='" + field + '\'' +
                "} " + super.toString();
    }
}
