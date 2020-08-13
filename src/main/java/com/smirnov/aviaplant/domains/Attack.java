package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Attack")
public class Attack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attackID;

    @NotNull
    private String typeA;

    @NotNull
    private String corps;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AttackAircraftID")
    private Aircraft aircraft;

    public Attack() {
    }

    public Attack(@NotNull String typeA, @NotNull String corps) {
        this.typeA = typeA;
        this.corps = corps;
    }

    public Attack(@NotNull String typeA, @NotNull String corps, Aircraft aircraft) {
        this.typeA = typeA;
        this.corps = corps;
        this.aircraft = aircraft;
    }

    public String getTypeA() {
        return typeA;
    }

    public Long getAttackID() {
        return attackID;
    }

    public void setAttackID(Long attackID) {
        this.attackID = attackID;
    }

    public void setTypeA(String typeA) {
        this.typeA = typeA;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
}
