package com.example.supperapp.dao;

import com.example.supperapp.dto.RoleDto;

import java.util.List;

public interface RoleDao {
    List<RoleDto> findRolesByUserId(String userId);
    List<RoleDto> findAll();
    void addUserRole(String userId, String roleId);
    void removeUserRole(String userId, String roleId);
}
