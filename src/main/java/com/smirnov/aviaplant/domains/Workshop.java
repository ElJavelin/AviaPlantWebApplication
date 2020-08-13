package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Workshops")
public class Workshop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workshopID")
    private Long workshopID;

    @NotNull
    private String nameOfWorkshop;

    @NotNull
    private String address;

    @NotNull
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workshop")
    private List<Sector> sectors = new ArrayList<>();


    public Workshop() {
    }

    public Workshop(@NotNull String nameOfWorkshop, @NotNull String address, @NotNull String telephone) {
        this.nameOfWorkshop = nameOfWorkshop;
        this.address = address;
        this.telephone = telephone;
    }

    public void setIdWorkshop(Long workshopID) {
        this.workshopID = workshopID;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public Long getWorkshopID() {
        return workshopID;
    }

    public String getNameOfWorkshop() {
        return nameOfWorkshop;
    }

    public void setNameOfWorkshop(String nameOfWorkshop) {
        this.nameOfWorkshop = nameOfWorkshop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}