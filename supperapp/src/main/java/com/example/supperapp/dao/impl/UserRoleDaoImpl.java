package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.UserRoleDao;
import com.example.supperapp.dto.UserRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserRoleDto> findRolesByUserId(String userId) {
        String sql ="""
                        SELECT r.id, r.role_name
                        FROM tbl_role r
                        INNER JOIN tbl_user_role ur ON r.id = ur.role_id
                        WHERE ur.user_id = ?
                    """;

        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) ->
                new UserRoleDto(rs.getString("id"), rs.getString("role_name")));
    }

    @Override
    public List<UserRoleDto> findAll() {
        String sql = "SELECT id, role_name FROM tbl_role";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new UserRoleDto(rs.getString("id"), rs.getString("role_name")));
    }

    @Override
    public void addUserRole(String userId, String roleId) {
        String sql = "INSERT INTO tbl_user_role (user_id, role_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, roleId);
    }

    @Override
    public void removeUserRole(String userId, String roleId) {
        String sql = "DELETE FROM tbl_user_role WHERE user_id = ? AND role_id = ?";
        jdbcTemplate.update(sql, userId, roleId);
    }
}