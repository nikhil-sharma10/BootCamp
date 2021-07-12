package com.bootcampProject.BootcampProject.dto;

import com.bootcampProject.BootcampProject.domain.Address;
import com.bootcampProject.BootcampProject.domain.CommonAddress;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Set;
import javax.validation.constraints.*;
//@JsonIgnoreProperties(value = {"password","confirmPassword","companyAddress"})
public class SellerDTO extends BaseDTO {

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])(?=.*[A-Z]).{8,15}", message = "Password must be of minimum 8 characters and maximum 15 "+
    "Characters and must contain 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character")
    private String password;
    @NotEmpty(message = "Confirm Password is required")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])(?=.*[A-Z]).{8,15}", message = "Password must be of minimum 8 characters and maximum 15 "+
            "Characters and must contain 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character")
    private String confirmPassword;
    @NotEmpty(message = "Must provide gst number")
    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$", message = "The first 2 digits denote the State Code (01-37) as defined in the Code List for Land Regions.\n" +
            "\n" +
            "The next 10 characters pertain to PAN Number in AAAAA9999X format.\n" +
            "\n" +
            "13th character indicates the number of registrations an entity has within a state for the same PAN.\n" +
            "\n" +
            "14th character is currently defaulted to \"Z\"\n" +
            "\n" +
            "15th character is a checksum digit")
    private String gst;
    @NotEmpty(message = "Company Name is required")
    private String companyName;

    @NotEmpty(message = "Company Address is required")
    private Set<Address> companyAddress;
    @NotEmpty(message = "Company Contact is required")
    @Pattern(regexp = "\\d{10}", message = "Mobile number is invalid")
    private String companyContact;
    @NotEmpty(message = "First Name is required")
    private String firstName;
    @NotEmpty(message = "Last Name is required")
    private String lastName;

    public SellerDTO(@NotEmpty(message = "Email is required") @Email(message = "Email is not valid") String email, @NotEmpty(message = "Company Name is required") String companyName, @NotEmpty(message = "Company Address is required") Set<Address> companyAddress, @NotEmpty(message = "Company Contact is required") @Pattern(regexp = "\\d{10}", message = "Mobile number is invalid") String companyContact, @NotEmpty(message = "First Name is required") String firstName, @NotEmpty(message = "Last Name is required") String lastName) {
        this.email = email;
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.companyContact = companyContact;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<Address> getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(Set<Address> companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
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
}
