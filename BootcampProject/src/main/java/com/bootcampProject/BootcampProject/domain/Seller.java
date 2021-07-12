package com.bootcampProject.BootcampProject.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name="user_id")
@JsonIgnoreProperties(value = {"product","password","addresses","createdAt","updatedAt","createdBy","updatedBy","active","deleted","userRole"})
public class Seller extends Users {
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

    public Seller() {
    }

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
