package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.CategoryMetadataField;
import com.bootcampProject.BootcampProject.dto.CategoryMetaDataFieldDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryMetaDataConverter implements DTOTransform<CategoryMetadataField, CategoryMetaDataFieldDTO> {

    @Override
    public CategoryMetaDataFieldDTO toDTO(CategoryMetadataField domainBase) {
        return new CategoryMetaDataFieldDTO(domainBase.getId(),domainBase.getName());
    }

    @Override
    public CategoryMetadataField fromDTO(CategoryMetaDataFieldDTO baseDTO) {
        CategoryMetadataField metadataField = new CategoryMetadataField();
        metadataField.setName(baseDTO.getName());
        return metadataField;
    }
}
