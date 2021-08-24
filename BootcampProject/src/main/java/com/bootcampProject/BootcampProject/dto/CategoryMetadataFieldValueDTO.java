package com.bootcampProject.BootcampProject.dto;

import java.util.List;
import java.util.UUID;

public class CategoryMetadataFieldValueDTO extends BaseDTO {
    private UUID category;
    private UUID categoryMetadataField;
    private List<String> values;

    public CategoryMetadataFieldValueDTO() {
    }

    public CategoryMetadataFieldValueDTO(UUID category, UUID categoryMetadataField, List<String> values) {
        this.category = category;
        this.categoryMetadataField = categoryMetadataField;
        this.values = values;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public UUID getCategoryMetadataField() {
        return categoryMetadataField;
    }

    public void setCategoryMetadataField(UUID categoryMetadataField) {
        this.categoryMetadataField = categoryMetadataField;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
