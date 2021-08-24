package com.bootcampProject.BootcampProject.convertor;

import com.bootcampProject.BootcampProject.common.DTOTransform;
import com.bootcampProject.BootcampProject.domain.Category;
import com.bootcampProject.BootcampProject.dto.CategoryDTO;
import com.bootcampProject.BootcampProject.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryCoverter implements DTOTransform<Category, CategoryDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO toDTO(Category domainBase) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(domainBase.getId());
        categoryDTO.setName(domainBase.getName());
        if(domainBase.getParentCategory() != null)
            categoryDTO.setParentCategory(this.toDTO(domainBase.getParentCategory()));
        else
            categoryDTO.setParentCategory(null);
        return categoryDTO;
    }

    @Override
    public Category fromDTO(CategoryDTO baseDTO) {
        Category category = new Category();
        category.setName(baseDTO.getName());
        if(baseDTO.getParentCategory()!= null)
            category.setParentCategory(categoryRepository.findById(baseDTO.getParentCategory().getId()).get());
        else
            category.setParentCategory(null);
        return category;
    }
}
