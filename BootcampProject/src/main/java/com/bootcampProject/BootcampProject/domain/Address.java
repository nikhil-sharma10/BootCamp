package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Address extends BaseDomain {

    @Embedded
    private CommonAddress commonAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public CommonAddress getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(CommonAddress commonAddress) {
        this.commonAddress = commonAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
