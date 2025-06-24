package com.example.supperapp.service.impl;

import com.example.supperapp.dao.RoleDao;
import com.example.supperapp.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao userRoleDao;

    public RoleServiceImpl(RoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public void addRoles(String userId, List<String> roleIds) {
        if (roleIds != null && !roleIds.isEmpty()) {
            userRoleDao.addRolesToUser(userId, roleIds);
        }
    }

    @Override
    public void removeRoles(String userId, List<String> roleIds) {
        if (roleIds != null && !roleIds.isEmpty()) {
            userRoleDao.removeRolesFromUser(userId, roleIds);
        }
    }
}

