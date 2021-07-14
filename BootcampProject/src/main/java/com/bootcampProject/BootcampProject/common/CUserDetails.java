package com.bootcampProject.BootcampProject.common;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class CUserDetails extends User {

    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public CUserDetails(String username, String password, boolean enabled,boolean accountNonExpired,boolean credentialsNonExpired,boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, UUID userId) {
        super(username, password, enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
        setUserId(userId);
    }
}
