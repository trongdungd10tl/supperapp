package com.example.supperapp.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String phone;
    private Long partnerId;
    private String partnerName;
    private Long categoryId;
    private String categoryName;
    private Long channelId;
    private String channelName;
}
