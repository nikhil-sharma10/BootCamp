package com.bootcampProject.BootcampProject.domain;

//import com.bootcampProject.BootcampProject.convertor.StringListConverter;

import com.bootcampProject.BootcampProject.convertor.StringListConverter;

import javax.persistence.*;
import java.util.List;

@Entity
public class CategoryMetadataFieldValue extends BaseDomain{

    @OneToOne
    @JoinColumn(name = "category_metadata_field_id")
    private CategoryMetadataField categoryMetadataField;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Convert(converter = StringListConverter.class)
    private List<String> values;

    public CategoryMetadataField getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(CategoryMetadataField categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
