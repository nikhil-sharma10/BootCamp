package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Seller extends User{
    @NotNull
    @Column(nullable = false)
    private String gst;
    @NotNull
    @Column(nullable = false)
    private String companyName;
    @NotNull
    @Column(nullable = false)
    private String companyContact;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    List<Product> product;


    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}
