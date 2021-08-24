package com.bootcampProject.BootcampProject.domain;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class CategoryMetadataField extends BaseDomain {

    @NotNull
    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "categoryMetadataField")
    private CategoryMetadataFieldValue categoryMetadataFieldValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryMetadataFieldValue getCategoryMetadataFieldValue() {
        return categoryMetadataFieldValue;
    }

    public void setCategoryMetadataFieldValue(CategoryMetadataFieldValue categoryMetadataFieldValue) {
        this.categoryMetadataFieldValue = categoryMetadataFieldValue;
    }
}
