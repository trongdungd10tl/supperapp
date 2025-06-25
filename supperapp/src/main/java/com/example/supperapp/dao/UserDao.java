package com.example.supperapp.dao;

import com.example.supperapp.dto.UserDto;
import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<UserDto> findAll();

    Optional<UserDto> findById(String id);

    void save(User user);

    void update(String id, UserUpdateDto userUpdateDto);

    Optional<UserUpdateDto> getUserUpdateAtForm(String id);

    void delete(String id);

    List<UserDto> filterUsersWithPagination(String name, String email, String phone, int offset, int size);

    int countUsersWithFilter(String name, String email, String phone);

    void updatePassword(String userId, String encodedPassword);
}
