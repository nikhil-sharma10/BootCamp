package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.Category;
import com.bootcampProject.BootcampProject.domain.CategoryMetadataField;
import com.bootcampProject.BootcampProject.domain.CategoryMetadataFieldValue;
import com.bootcampProject.BootcampProject.dto.CategoryMetadataFieldValueDTO;
import com.bootcampProject.BootcampProject.repository.CategoryMetadataFieldRepository;
import com.bootcampProject.BootcampProject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMetadataFieldValueConverter implements DTOTransform<CategoryMetadataFieldValue, CategoryMetadataFieldValueDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Override
    public CategoryMetadataFieldValueDTO toDTO(CategoryMetadataFieldValue domainBase) {
        return new CategoryMetadataFieldValueDTO(domainBase.getCategory().getId(),domainBase.getCategoryMetadataField().getId(),domainBase.getValues());
    }

    @Override
    public CategoryMetadataFieldValue fromDTO(CategoryMetadataFieldValueDTO baseDTO) {
        CategoryMetadataFieldValue categoryMetadataFieldValue = new CategoryMetadataFieldValue();
        Category category = categoryRepository.findById(baseDTO.getCategory()).get();
        CategoryMetadataField categoryMetadataField = categoryMetadataFieldRepository.findById(baseDTO.getCategoryMetadataField()).get();
        categoryMetadataFieldValue.setValues(baseDTO.getValues());
        categoryMetadataFieldValue.setCategory(category);
        categoryMetadataFieldValue.setCategoryMetadataField(categoryMetadataField);
        return categoryMetadataFieldValue;
    }
}
