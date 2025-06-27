package com.example.supperapp.service;

import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> filterRolesWithPagination(String role_name, String description, int page, int size );
    int countRolesWithFilter(String role_name, String description);
    void createRole(Role role);
    void deleteRole(String id);
    void updateRole(String id, Role role);
    Optional<Role> getRoleUpdateAtForm(String id);
}
