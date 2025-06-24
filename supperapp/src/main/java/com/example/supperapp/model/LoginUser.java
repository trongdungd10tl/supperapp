package com.example.supperapp.model;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser {
    private Long id;
    private String username;
    private String password;
    private String role;
}

