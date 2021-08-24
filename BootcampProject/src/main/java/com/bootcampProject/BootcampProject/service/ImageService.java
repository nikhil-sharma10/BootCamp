package com.bootcampProject.BootcampProject.service;

import com.bootcampProject.BootcampProject.domain.ImageTable;
import com.bootcampProject.BootcampProject.domain.ProductVariation;
import com.bootcampProject.BootcampProject.domain.Users;
import com.bootcampProject.BootcampProject.exceptions.FileUploadException;
import com.bootcampProject.BootcampProject.repository.ImageTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class ImageService extends BaseService {

    private final Path fileLocation;

    @Autowired
    private ImageTableRepository imageTableRepository;

    public ImageService(ImageTable imageTable){

        this.fileLocation = Paths.get(imageTable.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        }
        catch (Exception ex){
            throw new FileUploadException("Not able to create directory",ex);
        }
    }

    public String storeFile(MultipartFile file, Users user, boolean isProfile, ProductVariation productVariation){
        if(file != null){
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String fileName = "";
            try{
                if(fileExtension.contains("png") || fileExtension.contains("jpg") || fileExtension.contains("jpeg")){
                    if(isProfile){
                        fileName = user.getEmail() + "_" + "profileImage" + fileExtension;
                        Path targetLoaction = this.fileLocation.resolve(fileName);
                        Files.copy(file.getInputStream(),targetLoaction, StandardCopyOption.REPLACE_EXISTING);
                        ImageTable image = imageTableRepository.findByUserId(user);
                        if(image != null){
                            image.setFileName(fileName);
                            imageTableRepository.save(image);
                        }
                        else{
                            ImageTable imageTable = new ImageTable();
                            imageTable.setUserId(user);
                            imageTable.setFileName(fileName);
                            imageTableRepository.save(imageTable);
                        }
                        return fileName;
                    }
                    else {
                        fileName = productVariation.getId() + "_" + "ProductVariation" + fileExtension;
                        Path targetLoaction = this.fileLocation.resolve(fileName);
                        Files.copy(file.getInputStream(),targetLoaction, StandardCopyOption.REPLACE_EXISTING);
                        return fileName;
                    }

                }
                else{
                    throw new FileUploadException("File format not supported!!");
                }
            }
            catch (Exception ex){
                throw new FileUploadException("Could not store image", ex);
            }
        }
        else{
            return null;
        }

    }

    public URI loadImageAsResource(String fileName) throws Exception{
        try{
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource.getURI();
            }
            else {
                throw new FileNotFoundException("Image not found " + fileName);
            }
        }
        catch (MalformedURLException ex){
            throw new FileNotFoundException("Image Not found " + fileName);
        }
    }
}
