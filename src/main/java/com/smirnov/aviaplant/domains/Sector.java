package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Sectors")
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sectorID;

    @NotNull
    private String nameOfSector;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sector")
    private List<Union> unions = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sector_workshopID")
    private Workshop workshop;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sector")
    private List<Product> products = new ArrayList<>();

    public Sector() {}

    public Sector(String nameOfSector) {
        this.nameOfSector = nameOfSector;
    }

    public Sector(@NotNull String nameOfSector, Workshop workshop) {
        this.nameOfSector = nameOfSector;
        this.workshop = workshop;
    }

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public void setSectorID(Long sectorID) {
        this.sectorID = sectorID;
    }

    public List<Union> getUnions() {
        return unions;
    }

    public void setUnions(List<Union> unions) {
        this.unions = unions;
    }

    public void setNameOfSector(String nameOfSector) {
        this.nameOfSector = nameOfSector;
    }

    public Long getSectorID() {
        return sectorID;
    }

    public String getNameOfSector() {
        return nameOfSector;
    }

    public String getNameOfWorkshop(){
        return workshop.getNameOfWorkshop();
    }

    public Long getWorkshopID(){
        return workshop.getWorkshopID();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }


}
