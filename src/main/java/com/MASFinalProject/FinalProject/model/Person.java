package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long PersonId;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    @PastOrPresent
    private LocalDate timeOfStartingWorking;


    public Person() {

    }

    protected Person(@NotBlank String name, @NotBlank String surname) {
        setName(name);
        setSurname(surname);
        this.timeOfStartingWorking = LocalDate.now();
    }

    public long getPersonId() {
        return PersonId;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(@NotBlank String surname) {
        this.surname = surname;
    }

    public LocalDate getTimeOfStartingWorking() {
        return timeOfStartingWorking;
    }

    public void setTimeOfStartingWorking(LocalDate timeOfStartingWorking) {
        this.timeOfStartingWorking = timeOfStartingWorking;
    }

    public LocalDate getTotalWorkingTime() {
        return LocalDate.now().minusDays(timeOfStartingWorking.toEpochDay());
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", timeOfStartingWorking=" + timeOfStartingWorking +
                '}';
    }
}
