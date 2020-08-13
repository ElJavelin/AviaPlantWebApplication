package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Unions")
public class Union {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long unionID;

    @NotNull
    private String nameOfTheUnion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UnionsSectorID")
    private Sector sector;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "union")
    private List<Employee> employees = new ArrayList<>();

    public Union() {}

    public Union(String nameOfTheUnion) {
        this.nameOfTheUnion = nameOfTheUnion;
    }

    public Union(@NotNull String nameOfTheUnion, Sector sector) {
        this.nameOfTheUnion = nameOfTheUnion;
        this.sector = sector;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Long getUnionID() {
        return unionID;
    }

    public void setUnionID(Long unionID) {
        this.unionID = unionID;
    }

    public String getNameOfTheUnion() {
        return nameOfTheUnion;
    }

    public void setNameOfTheUnion(String nameOfTheUnion) {
        this.nameOfTheUnion = nameOfTheUnion;
    }

    public String getNameOfSector(){
        return sector.getNameOfSector();
    }

    public Long getSectorID(){
        return sector.getSectorID();
    }
}
