package com.smirnov.aviaplant.domains;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "Employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employeeID")
    private Long employeeID;

    @NotNull
    private String typeOfEmployee;

    @NotNull
    private String name;

    @NotNull
    private String patronymic;

    @NotNull
    private String lastname;

    @NotNull
    private java.sql.Date dob;

    @NotNull
    private String sex;

    @NotNull
    private String post;

    @NotNull
    private String education;

    @NotNull
    private String experience;

    @NotNull
    private java.sql.Date dateOfEntry;

    @NotNull
    private String salary;

    @NotNull
    private String status;

    @NotNull
    private Boolean headOfUnion;

    @NotNull
    private String fileName = "default_avatar.jpg";

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EmployeeUnionID")
    private Union union;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
    private UserData userData;

    public Employee() {
    }

    public Employee(String typeOfEmployee, String name, String patronymic, String lastname, java.sql.Date dob, String sex, String post, String education, String experience, java.sql.Date dateOfEntry, String salary, String status, Boolean headOfUnion) {
        this.typeOfEmployee = typeOfEmployee;
        this.name = name;
        this.patronymic = patronymic;
        this.lastname = lastname;
        this.dob = dob;
        this.sex = sex;
        this.post = post;
        this.education = education;
        this.experience = experience;
        this.dateOfEntry = dateOfEntry;
        this.salary = salary;
        this.status = status;
        this.headOfUnion = headOfUnion;
    }


    public Employee(Union union, String typeOfEmployee, String name, String patronymic, String lastname, java.sql.Date dob, String sex, String post, String education, String experience, java.sql.Date dateOfEntry, String salary, String status, Boolean headOfUnion) {
        this.union = union;
        this.typeOfEmployee = typeOfEmployee;
        this.name = name;
        this.patronymic = patronymic;
        this.lastname = lastname;
        this.dob = dob;
        this.sex = sex;
        this.post = post;
        this.education = education;
        this.experience = experience;
        this.dateOfEntry = dateOfEntry;
        this.salary = salary;
        this.status = status;
        this.headOfUnion = headOfUnion;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public String getTypeOfEmployee() {
        return typeOfEmployee;
    }

    public void setTypeOfEmployee(String typeOfEmployee) {
        this.typeOfEmployee = typeOfEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Date getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Date dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getHeadOfUnion() {
        return headOfUnion;
    }

    public void setHeadOfUnion(Boolean headOfUnion) {
        this.headOfUnion = headOfUnion;
    }

    public Union getUnion() {
        return union;
    }

    public void setUnion(Union union) {
        this.union = union;
    }

    public String getUnionName() {
        return union.getNameOfTheUnion();
    }

    public Long getUnionID() {
        return union.getUnionID();
    }

    public String getWorkshopName() {
        return union.getSector().getWorkshop().getNameOfWorkshop();
    }



/*
    public String getMainRole() {
        if (userData.getRoles().contains("ADMIN")) return "ADMIN";
        else if (userData.getRoles().contains("ADVANCED")) return "ADVANCED";
        else return "USER";
    }
*/
}
