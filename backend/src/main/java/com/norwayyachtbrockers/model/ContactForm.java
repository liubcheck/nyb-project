package com.norwayyachtbrockers.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactForm {

    @NotNull(message = "Email is required.")
    @Email(message = "Please provide a valid email format.")
    private String userEmail;

    @NotNull(message = "Name is required.")
    @Size(max = 30, message = "Name must not exceed 30 characters.")
    private String name;

    @NotNull(message = "Message is required.")
    @Size(max = 5000, message = "Message cannot exceed 5000 characters")
    private String message;
}
