package com.example.supperapp.service.impl;

import com.example.supperapp.dao.UserDao;
import com.example.supperapp.dto.UserDto;
import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.model.User;
import com.example.supperapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<UserDto> getAllUser() {
        return userDao.findAll();
    }

    @Override
    public Optional<UserDto> getUserById(String id) {
        return userDao.findById(id);
    }

    @Override
    public void createUser(User user) {
        userDao.save(user);
    }

    @Override
    public void updateUser(String id, UserUpdateDto userUpdateDto) {
        userDao.update(id, userUpdateDto);
    }

    @Override
    public Optional<UserUpdateDto> getUserUpdateAtForm(String id){ return userDao.getUserUpdateAtForm(id);}

    @Override
    public void deleteUser(String id) {
        userDao.delete(id);
    }

    @Override
    public List<UserDto> filterUsersWithPagination(String name, String email, String phone, int page, int size) {
        int offset = (page - 1) * size;
        return userDao.filterUsersWithPagination(name, email, phone, offset, size);
    }

    @Override
    public int countUsersWithFilter(String name, String email, String phone) {
        return userDao.countUsersWithFilter(name, email, phone);
    }


    @Override
    public void updatePassword(String userId, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        userDao.updatePassword(userId, encodedPassword);
    }
}
