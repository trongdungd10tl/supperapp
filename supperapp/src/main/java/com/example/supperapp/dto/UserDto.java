package com.example.supperapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private String roleName;
    private String partnerName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
