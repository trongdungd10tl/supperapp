package com.example.supperapp.dao;

import java.util.List;

public interface RoleDao {
    void addRolesToUser(String userId, List<String> roleIds);
    void removeRolesFromUser(String userId, List<String> roleIds);
}

