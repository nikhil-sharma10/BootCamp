package com.bootcampProject.BootcampProject.dto;

import java.util.UUID;

public class RoleDTO extends BaseDTO {
    private String authority;
    private UUID userID;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
}
