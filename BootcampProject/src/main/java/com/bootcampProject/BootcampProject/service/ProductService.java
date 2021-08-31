package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.convertor.ProductConverter;
import com.bootcampProject.BootcampProject.convertor.ProductVariationConverter;
import com.bootcampProject.BootcampProject.convertor.ProductVariationViewConverter;
import com.bootcampProject.BootcampProject.convertor.ProductViewDTOConverter;
import com.bootcampProject.BootcampProject.domain.*;
import com.bootcampProject.BootcampProject.dto.ProductDTO;
import com.bootcampProject.BootcampProject.dto.ProductVariationDTO;
import com.bootcampProject.BootcampProject.dto.ProductVariationViewDTO;
import com.bootcampProject.BootcampProject.dto.ProductViewDTO;
import com.bootcampProject.BootcampProject.exceptions.BadRequestException;
import com.bootcampProject.BootcampProject.repository.*;
import com.bootcampProject.BootcampProject.util.HeadersUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.*;

@Component
public class ProductService extends BaseService {

    private Map<String, List<String>> actualCategoryValueMap = new LinkedHashMap<>();
    private Map<String, List<String>> expectedCategoryValueMap = new LinkedHashMap<>();

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

    @Autowired
    private ProductVariationViewConverter productVariationViewConverter;

    @Autowired
    private UserRepository userRepository;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductService)) return false;
        ProductService that = (ProductService) o;
        return actualCategoryValueMap.equals(that.actualCategoryValueMap) &&
                expectedCategoryValueMap.equals(that.expectedCategoryValueMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actualCategoryValueMap, expectedCategoryValueMap);
    }

    @Transactional
    public String addProduct(ProductDTO productDTO, HttpServletRequest httpServletRequest){
        String userName = headersUtil.getUserName(httpServletRequest);
        Seller seller = sellerRepository.findByEmail(userName);
        List<Product> productList = seller.getProduct();
        Product newProduct = productConverter.fromDTO(productDTO);
        Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
        if(category != null){
            Role role = roleRepository.findByAuthority("ADMIN");
            if(!(category.getChildCategories().size() > 0)){
                if(productList.size() == 0 || !productList.contains(newProduct)){
                    newProduct.setSeller(seller);
                    newProduct.setDeleted(false);
                    newProduct.setActive(false);
                    productRepository.save(newProduct);
                    String message = "Product Added Successfully!!!";
//                    sendMail.sendMail(mailHeader,mailBody,role.getUserRole().getUsers().getEmail());
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
        else{
            throw new BadRequestException("Category is not valid");
        }

    }

    public ProductViewDTO getProduct(UUID productId, HttpServletRequest httpServletRequest){
        String userName = headersUtil.getUserName(httpServletRequest);
        Product product = productRepository.findById(productId).get();
        Users user = userRepository.findByEmail(userName);
        if(product != null){
            if(product.getSeller().getEmail().equals(userName)){
                if(!product.isDeleted()){
                    ProductViewDTO productViewDTO = productViewDTOConverter.toDTO(product);
                    return productViewDTO;
                }
                else {
                    throw new BadRequestException("Product doesn't exist");
                }

            }
            else if(user.getUserRole().getRole().getAuthority().equalsIgnoreCase("ROLE_ADMIN") || user.getUserRole().getRole().getAuthority().equalsIgnoreCase("ROLE_CUSTOMER")){
                ProductViewDTO productViewDTO = productViewDTOConverter.toDTO(product);
                Set<ProductVariationDTO> productVariationDTOSet = new LinkedHashSet<>();
                for(ProductVariation productVariation : product.getProductVariationList()){
                    productVariationDTOSet.add(productVariationConverter.toDTO(productVariation));
                }
                productViewDTO.setProductVariations(productVariationDTOSet);
                return productViewDTO;
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

    public String addProductVariation(MultipartFile file, String productVariationData) throws JsonProcessingException, ParseException {
        String message;
        ProductVariationDTO productVariationDTO = objectMapper.readValue(productVariationData, ProductVariationDTO.class);
        Product product = productRepository.findById(productVariationDTO.getProductId()).get();

        if(product != null){
            if(product.isActive()){
                if(!product.isDeleted()){
                    if(productVariationDTO.getPrice() >= 0){
                        if(productVariationDTO.getQuantityAvailable() >= 0){
//                            if(this.isValidMetaDataField(productVariationDTO)){
                            if(true){
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

    public ProductVariationViewDTO getProductVariation(HttpServletRequest httpServletRequest, UUID productVariationId){
        String userName = headersUtil.getUserName(httpServletRequest);
        ProductVariation productVariation = productVariationRepository.findById(productVariationId).get();

        if(productVariation != null){
            if(productVariation.getProduct().getSeller().getEmail().equals(userName)){
                if(!productVariation.getProduct().isDeleted()){
                    ProductVariationViewDTO responseData = productVariationViewConverter.toDTO(productVariation);
                    return responseData;
                }
                else{
                    throw new BadRequestException("Product variation doesn't exist");
                }
            }
            else{
                throw new BadRequestException("User is not authorized to view this product variation");
            }
        }
        else {
            throw new BadRequestException("Product Variation ID is not valid");
        }
    }

    public List<ProductVariationViewDTO> getAllProductVariation(HttpServletRequest httpServletRequest, UUID productId, Integer page, Integer size, String sortBy, String order){
        String userName = headersUtil.getUserName(httpServletRequest);
        Product product = productRepository.findById(productId).get();
        if(product != null){
            if(!product.isDeleted()) {
                List<ProductVariation> productVariationList = productVariationRepository.findAllByProduct(product, PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(order),sortBy)));
                List<ProductVariationViewDTO> responseData = new ArrayList<>();
                for(ProductVariation productVariation : productVariationList){
                    if(productVariation.getProduct().getSeller().getEmail().equals(userName)){
                        responseData.add(productVariationViewConverter.toDTO(productVariation));
                    }
                }
                return responseData;
            }
            else {
                throw new BadRequestException("Product doesn't exist");
            }
        }
        else {
            throw new BadRequestException("Product ID is not valid");
        }
    }

    public String updateProduct(HttpServletRequest httpServletRequest, UUID productId, ProductDTO productDTO){
        String userName = headersUtil.getUserName(httpServletRequest);
        Product product = productRepository.findById(productId).get();
        Seller seller = sellerRepository.findByEmail(userName);
        List<Product> productList = seller.getProduct();
        if(product != null){
            if(product.getSeller().getEmail().equals(userName)){
                if(productDTO.getProductName() != null){
                    if(this.isProductNameUnique(seller,productDTO.getProductName())){
                        product.setName(productDTO.getProductName());
                    }
                    else{
                        throw new BadRequestException("Product Name is not unique");
                    }
                }
                if(productDTO.getDescription() != null){
                    product.setDescription(productDTO.getDescription());
                }

                if(productDTO.isCancellable() != null){
                    product.setCancellable(productDTO.isCancellable());
                }

                if(productDTO.isReturnable() != null){
                    product.setReturnable(productDTO.isReturnable());
                }

                if(productDTO.getBrand() != null || productDTO.getCategoryId() != null){
                    throw new BadRequestException("Brand Name or Category can't be updated!!");
                }
                productRepository.save(product);
                String message = "Product Updated Successfully!!";
                return message;
            }
            else{
                throw new BadRequestException("User is not authorized to access this product");
            }
        }
        else{
            throw new BadRequestException("Product ID is not valid");
        }
    }

    public String updateProductVariation(HttpServletRequest httpServletRequest, UUID productVariationId, String productVariationDTO, MultipartFile file) throws JsonProcessingException, ParseException {
        String userName = headersUtil.getUserName(httpServletRequest);
        ProductVariationDTO productVariationDTO1 = objectMapper.readValue(productVariationDTO,ProductVariationDTO.class);
        ProductVariation productVariation = productVariationRepository.findById(productVariationId).get();
        if(productVariation != null){
            if(productVariation.getProduct().getSeller().getEmail().equals(userName)){
                if(productVariation.getProduct().isActive()){
                    if(!productVariation.getProduct().isDeleted()){
                        if(productVariationDTO1.getPrice() != null){
                            if(productVariationDTO1.getPrice() >= 0){
                                productVariation.setPrice(productVariationDTO1.getPrice());
                            }
                            else{
                                throw new BadRequestException("Product variation price can't be less than zero");
                            }
                        }

                        if(productVariationDTO1.getQuantityAvailable() != null){
                            if(productVariationDTO1.getQuantityAvailable() >= 0){
                                productVariation.setQuantityAvailable(productVariationDTO1.getQuantityAvailable());
                            }
                            else{
                                throw new BadRequestException("Product Variation Available quantity can't be less than zero");
                            }
                        }

                        if(productVariationDTO1.getMetaData() != null){
                            if(this.isValidMetaDataField(productVariationDTO1)){
                                productVariation.setMetadata(productVariationDTO1.getMetaData());
                            }
                            else{
                                throw new BadRequestException("Product variation metadata field map is not valid");
                            }
                        }

                        if(file != null){
                            String productVariationFileName = imageService.storeFile(file,null,false,productVariation);
                            productVariation.setPrimaryImageName(productVariationFileName);
                        }

                        productVariationRepository.save(productVariation);
                        String message = "Product Variation Saved Successfully!!!";
                        return message;
                    }
                    else{
                        throw new BadRequestException("Product should be non-deleted");
                    }
                }
                else{
                    throw new BadRequestException("Product is not active");
                }
            }
            else{
                throw new BadRequestException("Seller is not authorized to access this product variation");
            }
        }
        else{
            throw new BadRequestException("Product Variation Id is not valid");
        }
    }

    public List<ProductViewDTO> getAllProductByQuery(Integer page, Integer size, String sortBy, String order, String query, UUID id){
        if(query.equalsIgnoreCase("Category")){
            Category category = categoryRepository.findById(id).get();
            if(category != null && category.getChildCategories() == null){
                List<Product> productList = productRepository.findAllByCategory(category,PageRequest.of(page,size,Sort.by(Sort.Direction.fromString(order),sortBy))).getContent();
                List<ProductViewDTO>  productViewDTOList = new ArrayList<>();
                for(int i=0; i<productList.size(); i++){
                    productViewDTOList.add(productViewDTOConverter.toDTO(productList.get(i)));
                    Set<ProductVariationDTO> productVariationDTOSet = new LinkedHashSet<>();
                    for(ProductVariation productVariation : productList.get(i).getProductVariationList()){
                        productVariationDTOSet.add(productVariationConverter.toDTO(productVariation));
                    }
                    productViewDTOList.get(i).setProductVariations(productVariationDTOSet);
                }
                return productViewDTOList;
            }
            else{
                throw new BadRequestException("Category ID is not valid");
            }
        }
        else{
            if(query.equalsIgnoreCase("Seller")){
                Seller seller = sellerRepository.findById(id).get();
                if(seller != null){
                    List<Product> productList = seller.getProduct();
                    List<ProductViewDTO>  productViewDTOList = new ArrayList<>();
                    for(int i=0; i<productList.size(); i++){
                        productViewDTOList.add(productViewDTOConverter.toDTO(productList.get(i)));
                        Set<ProductVariationDTO> productVariationDTOSet = new LinkedHashSet<>();
                        for(ProductVariation productVariation : productList.get(i).getProductVariationList()){
                            productVariationDTOSet.add(productVariationConverter.toDTO(productVariation));
                        }
                        productViewDTOList.get(i).setProductVariations(productVariationDTOSet);
                    }
                    return productViewDTOList;
                }
                else{
                    throw new BadRequestException("Seller id is not valid");
                }
            }
            else{
                throw new BadRequestException("Query parameter is wrong");
            }
        }
    }


    private boolean isValidMetaDataField(ProductVariationDTO productVariationDTO) throws JsonProcessingException {
//        boolean isValid = false;
        Product product = productRepository.findById(productVariationDTO.getProductId()).get();
        Category category = product.getCategory();
        actualCategoryValueMap = objectMapper.readValue(productVariationDTO.getMetaData(), LinkedHashMap.class);

        if(actualCategoryValueMap.size() == 0){
            throw new BadRequestException("Metadata field value map should contain at least one value");
        }

        List<CategoryMetadataFieldValue> categoryMetadataFieldValueList = categoryMetadataFieldValueRepository.findByCategory(category);
        expectedCategoryValueMap = new LinkedHashMap<>();
        List<CategoryMetadataField> categoryMetadataFieldList = new ArrayList<>();
        for(CategoryMetadataFieldValue categoryMetadataFieldValue : categoryMetadataFieldValueList){
            if(categoryMetadataFieldValue.getCategory().equals(category)){
                expectedCategoryValueMap.put(categoryMetadataFieldValue.getCategoryMetadataField().getName(),categoryMetadataFieldValue.getValues());
            }
        }

        // Validate actualCategoryValue map contains the same element as expectedCategoryValueMap
        return expectedCategoryValueMap.entrySet().containsAll(actualCategoryValueMap.entrySet());
//        if(actualCategoryValueMap.size() <= expectedCategoryValueMap.size()){
//            for(Map.Entry<String,List<String>> actualMap : actualCategoryValueMap.entrySet()){
//                String actualKey = actualMap.getKey();
//                List<String> actualValues = actualMap.getValue();
//                for(Map.Entry<String,List<String>> expectedMap : expectedCategoryValueMap.entrySet()){
//                    String expectedKey = expectedMap.getKey();
//                    List<String> expectedValues = expectedMap.getValue();
//                    if(actualKey.equals(expectedKey) && expectedValues.contains(actualValues)){
//                        isValid = true;
//                        break;
//                    }
//                    else{
//                        isValid = false;
//                    }
//                }
//            }
//        }
//        return isValid;
    }

   private boolean isProductNameUnique(Seller seller, String productName){
        List<Product> productList = seller.getProduct();
        boolean isValid = true;
        for(Product product : productList){
            if(product.getName().equals(productName)){
                isValid = false;
                return isValid;
            }
        }
        return isValid;
   }

}
