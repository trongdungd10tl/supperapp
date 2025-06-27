package com.example.supperapp.dao;

import com.example.supperapp.dto.UserRoleDto;

import java.util.List;

public interface UserRoleDao {
    List<UserRoleDto> findRolesByUserId(String userId);
    List<UserRoleDto> findAll();
    void addUserRole(String userId, String roleId);
    void removeUserRole(String userId, String roleId);
}
