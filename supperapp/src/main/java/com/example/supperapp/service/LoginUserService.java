package com.example.supperapp.service;

import com.example.supperapp.model.LoginUser;

import java.util.Optional;

public interface LoginUserService {
    Optional<LoginUser> findByUsername(String username);
}
