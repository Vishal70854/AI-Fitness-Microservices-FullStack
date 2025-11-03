package com.fitness.gateway.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is required")    // if email is blank then validation will check and alert to provide email
    @Email(message = "Invalid email format")
    private String email;

    private String keycloakId;


    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must have atleast 6 characters") // minimum size of password must be 6 characters
    private String password;
    private String firstName;
    private String lastName;


}
