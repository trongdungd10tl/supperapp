package com.example.supperapp.service.impl;

import com.example.supperapp.dao.LoginUserDao;
import com.example.supperapp.model.LoginUser;
import com.example.supperapp.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private LoginUserDao loginUserDao;

    @Override
    public Optional<LoginUser> findByUsername(String username) {
        return loginUserDao.findByUsername(username);
    }
}
