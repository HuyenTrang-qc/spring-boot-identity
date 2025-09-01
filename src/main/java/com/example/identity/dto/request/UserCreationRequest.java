package com.example.identity.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreationRequest {
    private String id;

    @Size(min = 5, message = "USERNAME_INVALID")
    private String username;

    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

}
