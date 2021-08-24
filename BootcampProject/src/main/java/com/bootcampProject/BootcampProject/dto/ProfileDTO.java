package com.bootcampProject.BootcampProject.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.io.Resource;

import java.net.URI;

@JsonIgnoreProperties(value = {"createdAt","createdBy","updatedAt","updatedBy"})
public class ProfileDTO extends BaseDTO {

    private String firstName;
    private String lastName;
    private boolean isActive;
    private String contact;
    private URI image;

    public ProfileDTO(String firstName, String lastName, boolean isActive, String contact, URI image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.contact = contact;
        this.image = image;
    }

    public ProfileDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public URI getImage() {
        return image;
    }

    public void setImage(URI image) {
        this.image = image;
    }
}
