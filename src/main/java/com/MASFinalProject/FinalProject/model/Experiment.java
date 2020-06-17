package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Experiment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ExperimentId;

    @NotBlank
    private String title;
    @NotBlank
    private String target;
    @NotBlank
    private String result;

    @ManyToOne
    @JoinColumn(name = "SCPId",nullable = false)
    private SCP object;
    @ManyToOne
    @JoinColumn(name = "PersonId",nullable = false)
    private DClass subject;

    public Experiment(){

    }

    public Experiment(@NotBlank String title, @NotBlank String target, @NotBlank String result) {
        this.title = title;
        this.target = target;
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(@NotBlank String target) {
        this.target = target;
    }

    public String getResult() {
        return result;
    }

    public void setResult(@NotBlank String result) {
        this.result = result;
    }

    public SCP getObject() {
        return object;
    }



    public DClass getSubject() {
        return subject;
    }


}
