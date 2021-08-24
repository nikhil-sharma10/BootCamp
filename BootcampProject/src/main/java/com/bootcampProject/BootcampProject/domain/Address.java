package com.bootcampProject.BootcampProject.domain;

import javax.persistence.*;

@Entity
public class Address extends BaseDomain {

    @Embedded
    private CommonAddress commonAddress;


    private boolean isDeleted;


    public CommonAddress getCommonAddress() {
        return commonAddress;
    }

    public void setCommonAddress(CommonAddress commonAddress) {
        this.commonAddress = commonAddress;
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
