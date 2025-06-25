package com.example.supperapp.service.impl;

import com.example.supperapp.dao.RoleDao;
import com.example.supperapp.dto.RoleDto;
import com.example.supperapp.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleDto> getRolesAssignedToUser(String userId) {
        return roleDao.findRolesByUserId(userId);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public void addRoles(String userId, List<String> roleIds) {
        for (String roleId : roleIds) {
            roleDao.addUserRole(userId, roleId);
        }
    }

    @Override
    public void removeRoles(String userId, List<String> roleIds) {
        for (String roleId : roleIds) {
            roleDao.removeUserRole(userId, roleId);
        }
    }
}

