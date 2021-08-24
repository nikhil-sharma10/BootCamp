package com.bootcampProject.BootcampProject.domain;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class Users extends BaseDomain{

    @NotNull
    @Column(unique = true, nullable = false)
    private String email;
    @NotNull
    @Column(nullable = false)
    private String firstName;
    private String middleName;
    private String lastName;
    @NotNull
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(nullable = false)
    private boolean isDeleted;

    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Set<Address> addresses;

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private UserRole userRole;

    @NotNull
    @Column(nullable = false)
    private boolean isAccountNonLocked;

    private int failedLoginAttempt;

    public Users() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public int getFailedLoginAttempt() {
        return failedLoginAttempt;
    }

    public void setFailedLoginAttempt(int failedLoginAttempt) {
        this.failedLoginAttempt = failedLoginAttempt;
    }
}
