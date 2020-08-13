package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Transport")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transportID;

    @NotNull
    private String typeT;

    @NotNull
    private String carryingCapacity;

    @NotNull
    private String innerSpace;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TransportAircraftID")
    private Aircraft aircraft;

    public Transport() {
    }

    public Transport(@NotNull String typeT, @NotNull String carryingCapacity, @NotNull String innerSpace) {
        this.typeT = typeT;
        this.carryingCapacity = carryingCapacity;
        this.innerSpace = innerSpace;
    }

    public Transport(@NotNull String typeT, @NotNull String carryingCapacity, @NotNull String innerSpace, Aircraft aircraft) {
        this.typeT = typeT;
        this.carryingCapacity = carryingCapacity;
        this.innerSpace = innerSpace;
        this.aircraft = aircraft;
    }

    public Long getTransportID() {
        return transportID;
    }

    public void setTransportID(Long transportID) {
        this.transportID = transportID;
    }

    public String getTypeT() {
        return typeT;
    }

    public void setTypeT(String typeT) {
        this.typeT = typeT;
    }

    public String getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(String carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public String getInnerSpace() {
        return innerSpace;
    }

    public void setInnerSpace(String innerSpace) {
        this.innerSpace = innerSpace;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
}
