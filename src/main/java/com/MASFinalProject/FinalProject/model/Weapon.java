package com.MASFinalProject.FinalProject.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.EnumSet;
import java.util.Set;

@Entity
@Table(name = "Weapon")
public class Weapon implements ICaptureWeapon, INonLethalWeapon, ILethalWeapon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long WeaponId;

    @NotBlank
    private String name;
    @DecimalMin(value = "0.0")
    private double weight;
    @DecimalMin(value = "0.0")
    private float lethalPower;
    @Column(nullable = true)
    private String instructionsForCapturing;

    @NotEmpty
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<WeaponType> weaponTypes;
    @ManyToOne
    @JoinColumn(name = "PersonId",nullable = true)
    private MOGMember owner;
    public Weapon() {
    }

    public Weapon(@NotBlank String name, @DecimalMin(value = "0.0") double weight, @DecimalMin(value = "0.0") float lethalPower, String instructionsForCapturing, @NotEmpty EnumSet<WeaponType> weaponTypes) {

        setName(name);
        setWeight(weight);
        setWeaponTypes(weaponTypes);
        setLethalPower(lethalPower);
        setInstructions(instructionsForCapturing);

    }

    public String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(@DecimalMin(value = "0.0") double weight) {
        this.weight = weight;
    }

    private void setWeaponTypes(EnumSet<WeaponType> weaponTypes) {
        if (notLethalAndCapture(weaponTypes)) {
            this.weaponTypes = weaponTypes;
        }
    }

    @Override
    public String getInstructions() {
        if (!weaponTypes.contains(WeaponType.CAPTURE)) {
            throw new IllegalArgumentException("This is not a capture weapon");
        }
        return instructionsForCapturing;
    }

    @Override
    public void setInstructions(String instructions) {
        if (!weaponTypes.contains(WeaponType.CAPTURE)) {
            throw new IllegalArgumentException("This is not a capture weapon");
        }
        instructionsForCapturing = instructions;
    }

    @Override
    public float getLethalPower() {
        if (!weaponTypes.contains(WeaponType.LETHAL)) {
            throw new IllegalArgumentException("This is not a lethal weapon");
        }
        return lethalPower;
    }

    @Override
    public void setLethalPower(float power) {
        if (!weaponTypes.contains(WeaponType.LETHAL)) {
            throw new IllegalArgumentException("This is not a lethal weapon");
        }
        lethalPower = power;
    }

    @Override
    public float calculateStoppingPower() {
        if (!weaponTypes.contains((WeaponType.NONLETHAL))) {
            throw new IllegalArgumentException("This is not a non-lethal weapon");
        }

        return (float) (2 * Math.sqrt(weight));

    }

    private static boolean notLethalAndCapture(EnumSet<WeaponType> weaponTypeEnumSet) {
        if (!weaponTypeEnumSet.isEmpty()) {
            if (weaponTypeEnumSet.contains(WeaponType.LETHAL) && weaponTypeEnumSet.contains(WeaponType.CAPTURE)) {
                return false;
            }
            return true;
        }
        return true;
    }

    public MOGMember getOwner() {
        return owner;
    }

    public void setOwner(MOGMember owner) {
        this.owner = owner;
    }

    public Set<WeaponType> getWeaponTypes() {
        return weaponTypes;
    }

    public void setWeaponTypes(Set<WeaponType> weaponTypes) {
        this.weaponTypes = weaponTypes;
    }


}
