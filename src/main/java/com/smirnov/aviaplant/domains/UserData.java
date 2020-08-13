package com.smirnov.aviaplant.domains;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "UserData")
public class UserData implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userDataID;

    private String username;

    private String password;

    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "userRole", joinColumns = @JoinColumn(name = "userDataID"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserDataEmployeeID")
    private Employee employee;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "userData_dialog",
            joinColumns = @JoinColumn(name = "userDataID"),
            inverseJoinColumns = @JoinColumn(name = "dialogID"))
    private List<Dialog> dialogs;


    public UserData() {
    }

    public UserData(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;

    }

    public UserData(String username, String password, boolean active, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public Long getUserDataID() {
        return userDataID;
    }

    public void setUserDataID(Long userDataID) {
        this.userDataID = userDataID;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Dialog> getDialogs() {
        return dialogs;
    }

    public void setDialogs(List<Dialog> dialogs) {
        this.dialogs = dialogs;
    }
}
