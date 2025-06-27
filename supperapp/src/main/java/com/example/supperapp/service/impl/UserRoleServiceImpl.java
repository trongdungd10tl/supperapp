package com.example.supperapp.service.impl;

import com.example.supperapp.dao.UserRoleDao;
import com.example.supperapp.dto.UserRoleDto;
import com.example.supperapp.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleDao userRoleDao;

    public UserRoleServiceImpl(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public List<UserRoleDto> getRolesAssignedToUser(String userId) {
        return userRoleDao.findRolesByUserId(userId);
    }

    @Override
    public List<UserRoleDto> getAllRoles() {
        return userRoleDao.findAll();
    }

    @Override
    public void addRoles(String userId, List<String> roleIds) {
        for (String roleId : roleIds) {
            userRoleDao.addUserRole(userId, roleId);
        }
    }

    @Override
    public void removeRoles(String userId, List<String> roleIds) {
        for (String roleId : roleIds) {
            userRoleDao.removeUserRole(userId, roleId);
        }
    }
}

