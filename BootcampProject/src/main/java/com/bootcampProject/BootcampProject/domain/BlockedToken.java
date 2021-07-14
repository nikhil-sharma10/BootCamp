package com.bootcampProject.BootcampProject.domain;

import javax.persistence.Entity;

@Entity
public class BlockedToken extends BaseDomain {

    private String token;
    private boolean isBlocked;

    public BlockedToken(String token, boolean isBlocked) {
        this.token = token;
        this.isBlocked = isBlocked;
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
}
