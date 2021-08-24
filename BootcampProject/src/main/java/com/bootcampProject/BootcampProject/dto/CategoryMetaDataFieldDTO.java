package com.bootcampProject.BootcampProject.dto;

import java.util.UUID;

public class CategoryMetaDataFieldDTO extends BaseDTO {

    String name;

    public CategoryMetaDataFieldDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryMetaDataFieldDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
