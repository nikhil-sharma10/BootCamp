package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.convertor.ProductConverter;
import com.bootcampProject.BootcampProject.convertor.ProductVariationConverter;
import com.bootcampProject.BootcampProject.convertor.ProductViewDTOConverter;
import com.bootcampProject.BootcampProject.domain.*;
import com.bootcampProject.BootcampProject.dto.ProductDTO;
import com.bootcampProject.BootcampProject.dto.ProductVariationDTO;
import com.bootcampProject.BootcampProject.dto.ProductViewDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.*;
import com.bootcampProject.BootcampProject.util.HeadersUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class ProductService extends BaseService {

    final String mailHeader = "Product Activation";
    final String mailBody = "Please Activate product";

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HeadersUtil headersUtil;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private CategoryMetadataFieldValueRepository categoryMetadataFieldValueRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductVariationRepository productVariationRepository;

    @Autowired
    private ProductVariationConverter productVariationConverter;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductViewDTOConverter productViewDTOConverter;

    public String addProduct(ProductDTO productDTO, HttpServletRequest httpServletRequest){
        String userName = headersUtil.getUserName(httpServletRequest);
        Seller seller = sellerRepository.findByEmail(userName);
        List<Product> productList = seller.getProduct();
        Product newProduct = productConverter.fromDTO(productDTO);
        Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
        Role role = roleRepository.findByAuthority("ADMIN");
        if(category.getChildCategories() == null){
            if(!productList.contains(newProduct)){
                newProduct.setSeller(seller);
                newProduct.setDeleted(false);
                newProduct.setActive(false);
                productRepository.save(newProduct);
                String message = "Product Added Successfully!!!";
                sendMail.sendMail(mailHeader,mailBody,role.getUserRole().getUsers().getEmail());
                return message;
            }
            else {
                throw new BadRequestException("Product already exist");
            }
        }
        else {
            throw new BadRequestException("Category ID is not the leaf level category");
        }
    }

    public ProductViewDTO getProduct(UUID productId, HttpServletRequest httpServletRequest){
        String userName = headersUtil.getUserName(httpServletRequest);
        Product product = productRepository.findById(productId).get();
        if(product != null){
            if(product.getSeller().getEmail().equals(userName)){
                if(!product.isDeleted()){
                    ProductViewDTO productViewDTO = productViewDTOConverter.toDTO(product);
                    return productViewDTO;
                }
                else{
                    throw new BadRequestException("Product doesn't exist");
                }

            }
            else{
                throw new BadRequestException("User is not authorized to view this product");
            }
        }
        else{
            throw new BadRequestException("Product ID is not valid");
        }
    }

    public List<ProductViewDTO> getAllProducts(HttpServletRequest httpServletRequest, Integer page, Integer size, String sortBy, String order){
        String userName = headersUtil.getUserName(httpServletRequest);
        Seller seller = sellerRepository.findByEmail(userName);
        List<Product> productList = productRepository.findAllBySeller(seller, PageRequest.of(page,size, Sort.by(Sort.Direction.fromString(order),sortBy)));
        List<ProductViewDTO> responseData = new ArrayList<>();
        for(Product product : productList){
            if(!product.isDeleted()){
                responseData.add(productViewDTOConverter.toDTO(product));
            }
        }
        return responseData;
    }

    public String deleteProduct(UUID productId, HttpServletRequest httpServletRequest){
        String userName = headersUtil.getUserName(httpServletRequest);
        Product product = productRepository.findById(productId).get();
        if(product != null){
            if(product.getSeller().getEmail().equals(userName)){
                if(!product.isDeleted()){
                    product.setDeleted(true);
                    productRepository.save(product);
                    String message = "Product deleted Successfully!!!";
                    return message;
                }
                else{
                    throw new BadRequestException("Product doesn't exist");
                }

            }
            else{
                throw new BadRequestException("Product ID is not created by this user");
            }
        }
        else{
            throw new BadRequestException("Product ID is not valid");
        }
    }

    public String activateDeactivateProduct(UUID productID, boolean activate){
        Product product = productRepository.findById(productID).get();
        if(product != null ){
            if(activate && !product.isActive())
            product.setActive(true);
            else if(activate && product.isActive()){
                throw new BadRequestException("Product is already activated");
            }
            else if(!activate && product.isActive()){
                product.setActive(false);
            }
            else if(!activate && !product.isActive()){
                throw new BadRequestException("Product is already De Activated");
            }
            productRepository.save(product);
            String activateMessage = "Product Activated Successfully!!!";
            String deActivateMessage = "Product Deactivated Successfully!!!";
            return (activate)? activateMessage : deActivateMessage;
        }
        else {
            throw new BadRequestException("Product doesn't exist");
        }
    }

    public String addProductVariation(MultipartFile file, String productVariationData) throws JsonProcessingException {
        String message;
        ProductVariationDTO productVariationDTO = objectMapper.readValue(productVariationData, ProductVariationDTO.class);
        Product product = productRepository.findById(productVariationDTO.getProductId()).get();

        if(product != null){
            if(product.isActive()){
                if(!product.isDeleted()){
                    if(productVariationDTO.getPrice() >= 0){
                        if(productVariationDTO.getQuantityAvailable() >= 0){
                            if(this.isValidMetaDataField(productVariationDTO)){
                                ProductVariation productVariation = productVariationConverter.fromDTO(productVariationDTO);
                                String productVariationFileName = imageService.storeFile(file,null,false,productVariation);
                                productVariation.setPrimaryImageName(productVariationFileName);
                                productVariation.setActive(true);
                                productVariationRepository.save(productVariation);
                                message = "Product Variation Saved Successfully!!!";
                                return message;
                            }
                            else{
                                throw new BadRequestException("Product Variation map is not valid");
                            }
                        }
                        else {
                            throw new BadRequestException("Product Variation available quantity can't be less than zero");
                        }
                    }
                    else {
                        throw new BadRequestException("Product variation price can't be less than zero");
                    }
                }
                else {
                    throw new BadRequestException("Product is already deleted");
                }
            }
            else {
                throw new BadRequestException("Product is inactive");
            }
        }
        else {
            throw new BadRequestException("Product doesn't exist");
        }
    }

    private boolean isValidMetaDataField(ProductVariationDTO productVariationDTO) throws JsonProcessingException {
        boolean isValid = false;
        Product product = productRepository.findById(productVariationDTO.getProductId()).get();
        Category category = product.getCategory();
        String metaDataFieldValues;
        metaDataFieldValues = objectMapper.writeValueAsString(productVariationDTO.getMetaData());
        Map<String, List<String>> actualCategoryValueMap = objectMapper.readValue(metaDataFieldValues, LinkedHashMap.class);

        if(actualCategoryValueMap.size() == 0){
            throw new BadRequestException("Metadata field value map should contain at least one value");
        }

        List<CategoryMetadataFieldValue> categoryMetadataFieldValueList = categoryMetadataFieldValueRepository.findByCategory(category);
        Map<String, List<String>> expectedCategoryValueMap = new LinkedHashMap<>();
        List<CategoryMetadataField> categoryMetadataFieldList = new ArrayList<>();
        for(CategoryMetadataFieldValue categoryMetadataFieldValue : categoryMetadataFieldValueList){
            if(categoryMetadataFieldValue.getCategory().equals(category)){
                expectedCategoryValueMap.put(categoryMetadataFieldValue.getCategoryMetadataField().getName(),categoryMetadataFieldValue.getValues());
            }
        }

        // Validate actualCategoryValue map contains the same element as categoryValueMap
        if(actualCategoryValueMap.size() <= expectedCategoryValueMap.size()){
            for(Map.Entry<String,List<String>> actualMap : actualCategoryValueMap.entrySet()){
                String actualKey = actualMap.getKey();
                List<String> actualValues = actualMap.getValue();
                for(Map.Entry<String,List<String>> expectedMap : expectedCategoryValueMap.entrySet()){
                    String expectedKey = expectedMap.getKey();
                    List<String> expectedValues = expectedMap.getValue();
                    if(actualKey.equals(expectedKey) && expectedValues.contains(actualValues)){
                        isValid = true;
                        break;
                    }
                    else{
                        isValid = false;
                    }
                }
            }
        }
        return isValid;
    }

}
