package com.example.supperapp.model;


import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private Long partnerId;
    private Long channelId;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}