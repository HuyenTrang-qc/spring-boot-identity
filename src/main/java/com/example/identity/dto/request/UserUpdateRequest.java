package com.example.identity.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dob;

}
