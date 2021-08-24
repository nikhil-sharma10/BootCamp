package com.bootcampProject.BootcampProject.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class BlockedToken extends BaseDomain {

    private String token;
    private boolean isBlocked;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public BlockedToken() {
    }

    public BlockedToken(String token, boolean isBlocked, Users user) {
        this.token = token;
        this.isBlocked = isBlocked;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
