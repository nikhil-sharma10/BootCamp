package com.bootcampProject.BootcampProject.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class LogInDTO {

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])(?=.*[A-Z]).{8,15}", message = "Password must be of minimum 8 characters and maximum 15 "+
            "Characters and must contain 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character")
    private String password;

    public LogInDTO(@NotEmpty(message = "Email is required") @Email(message = "Email is not valid") String email, @NotEmpty(message = "Password is required") @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%])(?=.*[A-Z]).{8,15}", message = "Password must be of minimum 8 characters and maximum 15 " +
            "Characters and must contain 1 uppercase letter, 1 lowercase letter, 1 digit, and 1 special character") String password) {
        this.email = email;
        this.password = password;
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
}
