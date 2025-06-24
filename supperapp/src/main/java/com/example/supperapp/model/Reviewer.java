package com.example.supperapp.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Reviewer {
    private int id;
    private String msisdn;
    private String fullname;
    private LocalDateTime created_at;
    private String created_by;
}
