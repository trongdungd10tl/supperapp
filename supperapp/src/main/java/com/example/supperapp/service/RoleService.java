package com.example.supperapp.service;

import java.util.List;

public interface RoleService {
    void addRoles(String userId, List<String> roleIds);
    void removeRoles(String userId, List<String> roleIds);
}

