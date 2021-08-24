package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.common.ResponseBody;
import com.bootcampProject.BootcampProject.convertor.*;
import com.bootcampProject.BootcampProject.domain.*;
import com.bootcampProject.BootcampProject.dto.*;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.exceptions.NotFoundException;
import com.bootcampProject.BootcampProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService extends BaseService{

    private final String ACTIVATIONSUBJECT = "Account Activation";
    private final String DEACTIVATIONSUBJECT = "Account De-Activation";
    private final String ACTIVATIONMESSAGE = "Your account has been activated";
    private final String DEACTIVATIONMESSAGE = "Your account has been de-activated";

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    CustomerTransformer customerTransformer;

    @Autowired
    private CategoryMetadataFieldRepository categoryMetadataFieldRepository;

    @Autowired
    private CategoryMetaDataConverter categoryMetaDataConverter;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryCoverter categoryCoverter;

    @Autowired
    private CategoryMetadataFieldValueConverter categoryMetadataFieldValueConverter;

    @Autowired
    private CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;

    @Autowired
    SendMail sendMail;

    public ResponseBody<List<Customer>, String> getAllRegisteredCustomer(Integer page, Integer size, String sortBy){
        List<Customer> customers = customerRepository.findAll(PageRequest.of(page,size, Sort.by(sortBy))).getContent();
//        List<CustomerDTO> customerDTOList = customerTransformer.toDTO(customers);
        ResponseBody<List<Customer>,String> responseBody = new ResponseBody<>(customers,"Customers Fetched Successfully");
        return responseBody;
    }

    public ResponseBody<List<Seller>, String> getAllRegisteredSeller(Integer page, Integer size, String sortBy){
        List<Seller> sellers = sellerRepository.findAll(PageRequest.of(page,size,Sort.by(sortBy))).getContent();
        ResponseBody<List<Seller>,String> responseBody = new ResponseBody<>(sellers,"Sellers Fetched Successfully");
        return responseBody;

    }

    public String activateDeactivateUser(UUID id, boolean activate){
        Users user = userRepository.findById(id).get();
        final String activateMessage = "User Activated Successfully";
        final String deActivateMessage = "User De Activated Successfully";

        if(user != null){
            if(user.isActive() && activate){
                return activateMessage;
            }
            else if(!user.isActive() && activate){
                user.setActive(true);
                userRepository.save(user);
                sendMail.sendMail(ACTIVATIONSUBJECT,ACTIVATIONMESSAGE,user.getEmail());
                return activateMessage;
            }
            else if(user.isActive() && !activate){
                user.setActive(false);
                userRepository.save(user);
                sendMail.sendMail(DEACTIVATIONSUBJECT,DEACTIVATIONMESSAGE,user.getEmail());
                return deActivateMessage;
            }
            else if(!user.isActive() && !activate){
                return deActivateMessage;
            }
        }

        else{
            throw new NotFoundException("User not found!!");
        }
        return null;
    }


    public String addMetadataField(String metadataField){

        CategoryMetadataField categoryMetadataField = categoryMetadataFieldRepository.findByName(metadataField);
        String responseMessage = "";
        if(categoryMetadataField != null){
            CategoryMetadataField categoryMetadataField1 = new CategoryMetadataField();
            categoryMetadataField1.setName(metadataField);
            categoryMetadataFieldRepository.save(categoryMetadataField1);
            responseMessage = "New Meta Data field added";
            return responseMessage;
        }
        else{
            throw new BadRequestException("Field already exist");
        }
    }

    public List<CategoryMetaDataFieldDTO> getAllCategoryMetadataField(Integer page, Integer size, String sortBy, String order){
        List<CategoryMetadataField> categoryMetadataFields = categoryMetadataFieldRepository.findAll(PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(order),sortBy))).getContent();
        List<CategoryMetaDataFieldDTO> fieldDTOList = new ArrayList<>();

        for(CategoryMetadataField field : categoryMetadataFields){
            fieldDTOList.add(categoryMetaDataConverter.toDTO(field));
        }

        return fieldDTOList;
    }

    public String createNewCategory(CategoryDTO categoryDTO){

        Category category = categoryRepository.findByName(categoryDTO.getName());
        final String responseMessage = "Category created successfully";

        if(category == null){
            Category createdCategory = categoryCoverter.fromDTO(categoryDTO);
            Category parentCategory = (categoryDTO.getParentCategory() != null)? categoryRepository.findById(categoryDTO.getParentCategory().getId()).get() : null;

            if(parentCategory == null || (parentCategory.getProduct() == null || !parentCategory.getProduct().isActive())){
                categoryRepository.save(createdCategory);
                return responseMessage;
            }

            else{
                throw new BadRequestException("Can not create new category As Parent Category is already associated with an existing product");
            }
        }

        else{
            throw new BadRequestException("Category already exist");
        }
    }

    public CategoryDTO getCategory(UUID id){
        Category category = categoryRepository.findById(id).get();

        if(category != null){
            CategoryDTO categoryDTO = categoryCoverter.toDTO(category);
            return categoryDTO;
        }

        else {
            throw new BadRequestException("Category Id is invalid");
        }
    }

    public List<CategoryDTO> getAllCategories(Integer page, Integer size, String sortBy, String order){
        List<Category> categoryList = categoryRepository.findAll(PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(order), sortBy))).getContent();
        List<CategoryDTO> responseData = new ArrayList<>();
        for(Category category : categoryList){
            responseData.add(categoryCoverter.toDTO(category));
        }
        return responseData;
    }

    public String updateCategory(UUID categoryId, String categoryName){
        Category category = categoryRepository.findById(categoryId).get();
        Category uniqueCategory = categoryRepository.findByName(categoryName);
        String responseMessage = "";
        if(category != null){
            if(uniqueCategory == null){
                category.setName(categoryName);
                categoryRepository.save(category);
                responseMessage = "Category Name updated Successfully";
                return responseMessage;
            }
            else {
                throw new BadRequestException("Category Name already exist");
            }
        }
        else {
            throw new BadRequestException("Category ID is not valid");
        }
    }

    public String addMetaFieldValue(CategoryMetadataFieldValueDTO categoryMetadataFieldValueDTO){
        Category category = categoryRepository.findById(categoryMetadataFieldValueDTO.getCategory()).get();
        if(category != null){
            CategoryMetadataField categoryMetadataField = categoryMetadataFieldRepository.findById(categoryMetadataFieldValueDTO.getCategoryMetadataField()).get();
            if(categoryMetadataField != null){
                Set<String> valueSet = new HashSet<>(categoryMetadataFieldValueDTO.getValues());
                if(valueSet.size() == categoryMetadataFieldValueDTO.getValues().size()){
                    CategoryMetadataFieldValue categoryMetadataFieldValue = categoryMetadataFieldValueConverter.fromDTO(categoryMetadataFieldValueDTO);
                    categoryMetadataFieldValueRepository.save(categoryMetadataFieldValue);
                    String message = "Metadata Field Values Added Successfully";
                    return message;
                }
                else{
                    throw new BadRequestException("Metadata Field Values should be unique");
                }
            }
            else{
                throw new BadRequestException("Category Metadata field ID is not valid");
            }
        }
        else{
            throw new BadRequestException("Category ID is not valid");
        }
    }

    public String updateMetaFieldValue(CategoryMetadataFieldValueDTO categoryMetadataFieldValueDTO){
        Category category = categoryRepository.findById(categoryMetadataFieldValueDTO.getCategory()).get();

        if(category != null){
            CategoryMetadataField categoryMetadataField = categoryMetadataFieldRepository.findById(categoryMetadataFieldValueDTO.getCategoryMetadataField()).get();
            if(categoryMetadataField != null){
                CategoryMetadataFieldValue categoryMetadataFieldValue = categoryMetadataField.getCategoryMetadataFieldValue();
                Set<String> fieldValueSet = new HashSet<>(categoryMetadataFieldValueDTO.getValues());
                if(fieldValueSet.size() == categoryMetadataFieldValueDTO.getValues().size()){
                    Set<String> storedSet = new HashSet<>(categoryMetadataFieldValue.getValues());
                    Set<String> commonSet = new HashSet<>(storedSet);
                    commonSet.retainAll(fieldValueSet);
                    if(!(commonSet.size() >=1)){
                        categoryMetadataFieldValue.getValues().addAll(categoryMetadataFieldValueDTO.getValues());
                        String message = "Field Value Updated Successfully";
                        return message;
                    }
                    else {
                        throw new BadRequestException("Field value already exist: " + commonSet);
                    }
                }
                else {
                    throw new BadRequestException("Metadata field values should be unique");
                }
            }
            else{
                throw new BadRequestException("Category Metadata field ID is not valid");
            }
        }
        else{
            throw new BadRequestException("Category ID is not valid");
        }
    }
}
