package com.example.supperapp.service;

import com.example.supperapp.dto.UserDto;
import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDto> getAllUser();

    Optional<UserDto> getUserById(String id);

    void createUser(User user);

    void updateUser(String id, UserUpdateDto userUpdateDto);

    Optional<UserUpdateDto> getUserUpdateAtForm(String id);

    void deleteUser(String id);

    List<UserDto> filterUsersWithPagination(String name, String email, String phone, int page, int size);

    int countUsersWithFilter(String name, String email, String phone);

    void updatePassword(String userId, String newPassword);
}
