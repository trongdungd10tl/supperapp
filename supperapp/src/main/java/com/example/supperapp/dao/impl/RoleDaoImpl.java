package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.RoleDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private final JdbcTemplate jdbcTemplate;

    public RoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addRolesToUser(String userId, List<String> roleIds) {
        String sql = "INSERT INTO tbl_user_role (user_id, role_id) VALUES (?, ?) ON CONFLICT DO NOTHING";
        List<Object[]> batchArgs = new ArrayList<>();
        for (String roleId : roleIds) {
            batchArgs.add(new Object[]{userId, roleId});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }

    @Override
    public void removeRolesFromUser(String userId, List<String> roleIds) {
        String sql = "DELETE FROM tbl_user_role WHERE user_id = ? AND role_id = ?";
        List<Object[]> batchArgs = new ArrayList<>();
        for (String roleId : roleIds) {
            batchArgs.add(new Object[]{userId, roleId});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
}

