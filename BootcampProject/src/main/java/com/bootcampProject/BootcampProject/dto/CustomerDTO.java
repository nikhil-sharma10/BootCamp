package com.bootcampProject.BootcampProject.dto;

import com.bootcampProject.BootcampProject.domain.Address;
import com.bootcampProject.BootcampProject.domain.CommonAddress;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

public class CustomerDTO extends BaseDTO {

    @NotEmpty(message = "Must provide your first name")
    private String firstName;

    @NotEmpty(message = "Must provide your last name")
    private String lastName;


    private Set<Address> addresses;

    @NotEmpty(message = "Enter your email")
    @Email(message = "Email is not valid")
    private String email;

    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])(?=.*[A-Z]).{8,15}", message = "Password must be of minimum 8 characters and maximum 15 " +
            "characters and must contain 1 uppercase letter,1 lowercase letter,1 digit and 1 special character")
    private String password;

    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])(?=.*[A-Z]).{8,15}", message = "Password must be of minimum 8 characters and maximum 15 " +
            "characters and must contain 1 uppercase letter,1 lowercase letter,1 digit and 1 special character")
    private String confirmPassword;

    @NotEmpty(message = "Enter your mobile number")
    @Pattern(regexp = "\\d{10}", message = "Mobile number is invalid")
    private String mobileNo;

    private boolean activated;

    public CustomerDTO(@NotEmpty(message = "Must provide your first name") String firstName, @NotEmpty(message = "Must provide your last name") String lastName, @NotEmpty(message = "Enter your email") @Email(message = "Email is not valid") String email, boolean activated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
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


    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
