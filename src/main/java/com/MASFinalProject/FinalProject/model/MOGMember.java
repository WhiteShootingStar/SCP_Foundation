package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "PersonId")
public class MOGMember extends Person {


    @Min(20)
    @Max(65)
    private int age;
    @ElementCollection(fetch = FetchType.EAGER)
    @NotEmpty
    @NotNull
    private List<@NotBlank String> features;


    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "owner")
    private Set<Weapon> weaponSet = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="MOGTeamId",nullable = true)
    private MOGTeam hisTeam;
    public MOGMember() {

    }

    public MOGMember(@NotBlank String name, @NotBlank String surname, @Min(20) @Max(65) int age, @NotEmpty @NotNull List<@NotBlank String> features) {
        super(name, surname);
        this.age = age;
        this.features = features;
    }

    public int getAge() {
        return age;
    }

    public void setAge(@Min(20) @Max(65) int age) {
        this.age = age;
    }

    public void addFeature(@NotBlank String feature) {
        features.add(feature);
    }

    public void removeFeatureAtIndex(int index) {
        if (index < 0 || index >= features.size()) {
            throw new ArrayIndexOutOfBoundsException("Index is too big or too small");
        }
        features.remove(index);
    }

    public Set<Weapon> getWeaponSet() {
        return weaponSet;
    }

    public void setWeaponSet(Set<Weapon> weaponSet) {
        this.weaponSet = weaponSet;
    }

    public MOGTeam getHisTeam() {
        return hisTeam;
    }

    public void setHisTeam(MOGTeam hisTeam) {
        this.hisTeam = hisTeam;
    }
}
