package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
public class Role extends BaseDomain implements GrantedAuthority {
    @NotNull
    @Column(nullable = false)
    private String authority;

    @OneToOne(mappedBy = "role", cascade = CascadeType.ALL)
    private UserRole userRole;

    public Role() {
    }

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
