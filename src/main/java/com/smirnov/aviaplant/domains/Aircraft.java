package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Aircrafts")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aircraftID;

    @NotNull
    private String type;

    @NotNull
    private String model;

    @NotNull
    private Integer crew;

    @NotNull
    private String weight;

    @NotNull
    private String longA;

    @NotNull
    private String height;

    @NotNull
    private String width;

    @NotNull
    private String cruisingSpeed;

    @NotNull
    private String maxSpeed;

    @NotNull
    private Integer numberOfEngines;

    @NotNull
    private String engineType;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "AircraftProductID")
    private Product product;

    @OneToOne(cascade=CascadeType.ALL, mappedBy = "aircraft")
    private Civilian civilian;

    @OneToOne(cascade=CascadeType.ALL, mappedBy = "aircraft")
    private Attack attack;

    @OneToOne(cascade=CascadeType.ALL, mappedBy = "aircraft")
    private Transport transport;

    public Aircraft() {}

    public Aircraft(@NotNull String type, @NotNull String model, @NotNull Integer crew, @NotNull String weight, @NotNull String longA, @NotNull String height, @NotNull String width, @NotNull String cruisingSpeed, @NotNull String maxSpeed, @NotNull Integer numberOfEngines, @NotNull String engineType) {
        this.type = type;
        this.model = model;
        this.crew = crew;
        this.weight = weight;
        this.longA = longA;
        this.height = height;
        this.width = width;
        this.cruisingSpeed = cruisingSpeed;
        this.maxSpeed = maxSpeed;
        this.numberOfEngines = numberOfEngines;
        this.engineType = engineType;
    }

    public Aircraft(@NotNull String type, @NotNull String model, @NotNull Integer crew, @NotNull String weight, @NotNull String longA, @NotNull String height, @NotNull String width, @NotNull String cruisingSpeed, @NotNull String maxSpeed, @NotNull Integer numberOfEngines, @NotNull String engineType, Product product) {
        this.type = type;
        this.model = model;
        this.crew = crew;
        this.weight = weight;
        this.longA = longA;
        this.height = height;
        this.width = width;
        this.cruisingSpeed = cruisingSpeed;
        this.maxSpeed = maxSpeed;
        this.numberOfEngines = numberOfEngines;
        this.engineType = engineType;
        this.product = product;
    }

    public Long getAircraftID() {
        return aircraftID;
    }

    public void setAircraftID(Long aircraftID) {
        this.aircraftID = aircraftID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCrew() {
        return crew;
    }

    public void setCrew(Integer crew) {
        this.crew = crew;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLongA() {
        return longA;
    }

    public void setLongA(String longA) {
        this.longA = longA;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getCruisingSpeed() {
        return cruisingSpeed;
    }

    public void setCruisingSpeed(String cruisingSpeed) {
        this.cruisingSpeed = cruisingSpeed;
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getNumberOfEngines() {
        return numberOfEngines;
    }

    public void setNumberOfEngines(Integer numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Civilian getCivilian() {
        return civilian;
    }

    public void setCivilian(Civilian civilian) {
        this.civilian = civilian;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack attack) {
        this.attack = attack;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }
}
