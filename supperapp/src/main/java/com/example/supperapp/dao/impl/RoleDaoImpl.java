package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.RoleDao;
import com.example.supperapp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Role> filterRolesWithPagination(String roleName, String description, int offset, int size) {
        StringBuilder sql = new StringBuilder("SELECT id, role_name, description FROM tbl_role WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (roleName != null && !roleName.isBlank()) {
            sql.append(" AND role_name LIKE ?");
            params.add("%" + roleName + "%");
        }

        if (description != null && !description.isBlank()) {
            sql.append(" AND description LIKE ?");
            params.add("%" + description + "%");
        }

        sql.append(" ORDER BY id DESC LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);

        return jdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<>(Role.class));
    }




    @Override
    public int countRolesWithFilter(String roleName, String description) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_role r WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (roleName != null && !roleName.isEmpty()) {
            sql.append(" AND r.role_name LIKE ?");
            params.add("%" + roleName + "%");
        }
        if (description != null && !description.isEmpty()) {
            sql.append(" AND r.description LIKE ?");
            params.add("%" + description + "%");
        }

        return jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
    }


    @Override
    public void createRole(Role role) {
        String sql = "INSERT INTO tbl_role (role_name, description) VALUES (?, ?)";
        jdbcTemplate.update(sql, role.getRole_name(), role.getDescription());
    }

    @Override
    public void deleteRole(String id) {
        String sql = "DELETE FROM tbl_role WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateRole(String id, Role role) {
        String sql = "UPDATE tbl_role SET role_name = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(sql, role.getRole_name(), role.getDescription(), id);
    }


    @Override
    public Optional<Role> getRoleUpdateAtForm(String id) {
        String sql = """
        SELECT 
            id,
            role_name,
            description
        FROM tbl_role
        WHERE id = ?
    """;

        List<Role> roles = jdbcTemplate.query(sql, (rs, rowNum) -> Role.builder()
                .id(rs.getLong("id"))
                .role_name(rs.getString("role_name"))
                .description(rs.getString("description"))
                .build(), id);

        return roles.stream().findFirst();
    }

}
