package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Civilian")
public class Civilian {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long civilianID;

    @NotNull
    private String сType;

    @NotNull
    private String classA;

    @NotNull
    private Integer numOfSeats;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CivilianAircraftID")
    private Aircraft aircraft;

    public Civilian() {
    }

    public Civilian(@NotNull String type, @NotNull String classA, @NotNull Integer numOfSeats) {
        this.сType = type;
        this.classA = classA;
        this.numOfSeats = numOfSeats;
    }


    public Civilian(@NotNull String type, @NotNull String classA, @NotNull Integer numOfSeats, Aircraft aircraft) {
        this.сType = type;
        this.classA = classA;
        this.numOfSeats = numOfSeats;
        this.aircraft = aircraft;
    }

    public Long getCivilianID() {
        return civilianID;
    }

    public void setCivilianID(Long civilianID) {
        this.civilianID = civilianID;
    }

    public String getType() {
        return сType;
    }

    public void setType(String type) {
        this.сType = type;
    }

    public String getClassA() {
        return classA;
    }

    public void setClassA(String classA) {
        this.classA = classA;
    }

    public Integer getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(Integer numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
}
