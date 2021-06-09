package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product extends BaseDomain{

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;
    @NotNull
    @Column(nullable = false)
    private String name;
    private String description;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @NotNull
    @Column(nullable = false)
    private boolean isCancellable;
    @NotNull
    @Column(nullable = false)
    private boolean isReturnable;
    @NotNull
    @Column(nullable = false)
    private String brand;
    @NotNull
    @Column(nullable = false)
    private boolean isActive;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<ProductVariation> productVariationList;

//    @OneToOne(mappedBy = "product" ,cascade = CascadeType.ALL)
//    private ProductReview productReview;


    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }

    public boolean isReturnable() {
        return isReturnable;
    }

    public void setReturnable(boolean returnable) {
        isReturnable = returnable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<ProductVariation> getProductVariationList() {
        return productVariationList;
    }

    public void setProductVariationList(Set<ProductVariation> productVariationList) {
        this.productVariationList = productVariationList;
    }

//    public ProductReview getProductReview() {
//        return productReview;
//    }
//
//    public void setProductReview(ProductReview productReview) {
//        this.productReview = productReview;
//    }
}
