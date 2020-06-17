package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "PersonId")
public class DClass extends Person {
    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotNull
    @NotEmpty
    @ElementCollection
    @Size(min = 1)
    private List<@NotBlank String> listOfMurders;

    private static LocalDate disposalDate;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "subject")
    private Set<Experiment> subjectIn = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "DClassContainmentZoneId")
    private DClassContainmentZone containedIn;

    public DClass() {
    }

    public DClass(@NotBlank String name, @NotBlank String surname, @NotNull @Past LocalDate dateOfBirth, @NotNull @NotEmpty @Size(min = 1) List<@NotBlank String> listOfMurders) {
        super(name, surname);
        this.dateOfBirth = dateOfBirth;
        this.listOfMurders = listOfMurders;
    }

    static {
        setDisposalDate();
    }

    private static void setDisposalDate() {
        if (disposalDate == null) {
            disposalDate = LocalDate.now().plusDays(30);
        } else if (Duration.between(LocalDate.now(), disposalDate).toDays() > 30) { // more than 30 days passed
            disposalDate = LocalDate.now();
        }
    }

    public static LocalDate getDisposalDate() {
        return disposalDate;
    }

    public static void dispose() {
        /*
            Some methods to dispose of all D-class members if the date is correct
         */
        setDisposalDate();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<Experiment> getSubjectIn() {
        return subjectIn;
    }

    public void setSubjectIn(Set<Experiment> subjectIn) {
        this.subjectIn = subjectIn;
    }

    public DClassContainmentZone getContainedIn() {
        return containedIn;
    }

    public void setContainedIn(DClassContainmentZone containedIn) {
        this.containedIn = containedIn;
    }
}
