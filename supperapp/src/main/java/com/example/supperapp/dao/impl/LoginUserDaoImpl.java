package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.LoginUserDao;
import com.example.supperapp.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class LoginUserDaoImpl implements LoginUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<LoginUser> findByUsername(String username) {
        String sql = """
        SELECT 
            u.id,
            u.username,
            u.password,
            GROUP_CONCAT(r.role_name SEPARATOR ',') AS role
        FROM tbl_user u
        LEFT JOIN tbl_user_role ur ON u.id = ur.user_id
        LEFT JOIN tbl_role r ON ur.role_id = r.id
        WHERE u.username = ?
        GROUP BY u.id
    """;
        try {
            LoginUser loginUser = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(LoginUser.class),
                    username
            );
            return Optional.ofNullable(loginUser);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
