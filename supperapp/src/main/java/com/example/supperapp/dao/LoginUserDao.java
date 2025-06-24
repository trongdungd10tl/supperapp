package com.example.supperapp.dao;

import com.example.supperapp.model.LoginUser;

import java.util.Optional;

public interface LoginUserDao {
    Optional<LoginUser> findByUsername(String username);
}
