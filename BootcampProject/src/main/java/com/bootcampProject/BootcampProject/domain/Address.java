package com.bootcampProject.BootcampProject.domain;

import javax.persistence.*;

@Entity
public class Address extends BaseDomain {

    @Embedded
    private CommonAddress commonAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;


    public CommonAddress getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(CommonAddress commonAddress) {
        this.commonAddress = commonAddress;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


}
