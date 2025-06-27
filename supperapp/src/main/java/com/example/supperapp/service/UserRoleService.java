package com.example.supperapp.service;

import com.example.supperapp.dto.UserRoleDto;

import java.util.List;

public interface UserRoleService {
    List<UserRoleDto> getRolesAssignedToUser(String userId);
    List<UserRoleDto> getAllRoles();
    void addRoles(String userId, List<String> roleIds);
    void removeRoles(String userId, List<String> roleIds);
}

