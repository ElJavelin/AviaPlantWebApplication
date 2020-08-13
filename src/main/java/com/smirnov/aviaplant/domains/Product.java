package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "productID")
    private Long productID;

    @NotNull
    private String typeOfProduct;

    private String destinationCountry;

    private String price;

    @NotNull
    private java.sql.Date startDate;

    @NotNull
    private java.sql.Date plannedCompletionDate;

    @NotNull
    private String status;

    @NotNull
    private String fileName = "default_product_avatar.jpg";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductSectorID")
    private Sector sector;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "product")
    private Aircraft aircraft;

    public Product() {
    }

    public Product(@NotNull String typeOfProduct, String destinationCountry, String price, Date startDate, Date plannedCompletionDate, @NotNull String status) {
        this.typeOfProduct = typeOfProduct;
        this.destinationCountry = destinationCountry;
        this.price = price;
        this.startDate = startDate;
        this.plannedCompletionDate = plannedCompletionDate;
        this.status = status;
    }

    public Product(@NotNull String typeOfProduct, String destinationCountry, String price, Date startDate, Date plannedCompletionDate, @NotNull String status, Sector sector) {
        this.typeOfProduct = typeOfProduct;
        this.destinationCountry = destinationCountry;
        this.price = price;
        this.startDate = startDate;
        this.plannedCompletionDate = plannedCompletionDate;
        this.status = status;
        this.sector = sector;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public String getTypeOfProduct() {
        return typeOfProduct;
    }

    public void setTypeOfProduct(String typeOfProduct) {
        this.typeOfProduct = typeOfProduct;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getPlannedCompletionDate() {
        return plannedCompletionDate;
    }

    public void setPlannedCompletionDate(Date plannedCompletionDate) {
        this.plannedCompletionDate = plannedCompletionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Sector getSector() {
        return sector;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getSectorID() {
        return sector.getSectorID();
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getAircraftType(){
        return aircraft.getType();
    }

    public String getAircraftModel(){
        return aircraft.getModel();
    }
}
