package com.example.supperapp.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Role {
    private Long id;
    private String role_name;
    private String description;
}
