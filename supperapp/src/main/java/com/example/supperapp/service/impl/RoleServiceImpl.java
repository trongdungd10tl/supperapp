package com.example.supperapp.service.impl;

import com.example.supperapp.dao.RoleDao;
import com.example.supperapp.model.Role;
import com.example.supperapp.service.RoleMessagePublisher;
import com.example.supperapp.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    public final RoleDao roleDao;
    private final RoleMessagePublisher roleMessagePublisher;

    public RoleServiceImpl(RoleDao roleDao, RoleMessagePublisher roleMessagePublisher) {
        this.roleDao = roleDao;
        this.roleMessagePublisher = roleMessagePublisher;
    }

    @Override
    public List<Role> filterRolesWithPagination(String role_name, String description, int page, int size ){
        int offset = (page - 1) * size;
        return roleDao.filterRolesWithPagination(role_name,description, offset, size);
    }

    @Override
    public int countRolesWithFilter(String role_name, String description) {
        return roleDao.countRolesWithFilter(role_name, description);
    }

    @Override
    public void createRole(Role role){
        roleDao.createRole(role);
        roleMessagePublisher.sendRoleAction("CREATE", role);
    }

    @Override
    public void deleteRole(String id){
        Optional<Role> role = roleDao.getRoleUpdateAtForm(id);
        role.ifPresent(r -> roleMessagePublisher.sendRoleAction("DELETE", r));
        roleDao.deleteRole(id);
    }

    @Override
    public void updateRole(String id, Role role){
        roleDao.updateRole(id, role);
        roleMessagePublisher.sendRoleAction("UPDATE", role);
    }


    @Override
    public Optional<Role> getRoleUpdateAtForm(String id){
        return roleDao.getRoleUpdateAtForm(id);
    }


}
