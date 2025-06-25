package com.example.supperapp.service;

import com.example.supperapp.dto.RoleDto;

import java.util.List;

public interface RoleService {
    List<RoleDto> getRolesAssignedToUser(String userId);
    List<RoleDto> getAllRoles();
    void addRoles(String userId, List<String> roleIds);
    void removeRoles(String userId, List<String> roleIds);
}

