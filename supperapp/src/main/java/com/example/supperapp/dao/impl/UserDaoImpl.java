package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.UserDao;
import com.example.supperapp.dto.UserDto;
import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.mapper.UserMapper;
import com.example.supperapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<UserDto> findAll() {
        String sql = """
            SELECT 
                u.id,
                u.username,
                u.fullname,
                u.email,
                u.phone,
                u.created_at,
                u.updated_at,
                p.name,
                r.role_name
            FROM tbl_user u
            LEFT JOIN tbl_partner_v2 p ON u.partner_id = p.id
            LEFT JOIN tbl_user_role ur ON u.id = ur.user_id
            LEFT JOIN tbl_role r ON ur.role_id = r.id
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> UserDto.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .fullname(rs.getString("fullname"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                .partnerName(rs.getString("name"))
                .roleName(rs.getString("role_name"))
                .build()
        );
    }

    @Override
    public Optional<UserDto> findById(String id) {
        String sql = """
            SELECT 
                u.id,
                u.username,
                u.fullname,
                u.email,
                u.phone,
                u.created_at,
                u.updated_at,
                p.name,
                r.role_name
            FROM tbl_user u
            LEFT JOIN tbl_partner_v2 p ON u.partner_id = p.id
            LEFT JOIN tbl_user_role ur ON u.id = ur.user_id
            LEFT JOIN tbl_role r ON ur.role_id = r.id
            WHERE u.id = ?
        """;

        List<UserDto> users = jdbcTemplate.query(sql, (rs, rowNum) -> UserDto.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .fullname(rs.getString("fullname"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .updatedAt(rs.getTimestamp("updated_at").toLocalDateTime())
                .partnerName(rs.getString("name"))
                .roleName(rs.getString("role_name"))
                .build(), id);

        return users.stream().findFirst();
    }



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        String sql = """
            INSERT INTO tbl_user (
                username, fullname, email, password, phone, partner_id, channel_id, category_id, created_at, updated_at
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        jdbcTemplate.update(sql,
                user.getUsername(),
                user.getFullname(),
                user.getEmail(),
                encodedPassword,                // <-- dùng mật khẩu đã mã hóa
                user.getPhone(),
                user.getPartnerId(),
                user.getChannelId(),
                user.getCategoryId(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }


    @Override
    public void update(String id, UserUpdateDto userUpdateDto) {
        String sql = """
        UPDATE tbl_user 
        SET username = ?, 
            fullname = ?, 
            email = ?, 
            phone = ?, 
            partner_id = ?,
            category_id = ?, 
            channel_id = ?
        WHERE id = ?
    """;

        jdbcTemplate.update(sql,
                userUpdateDto.getUsername(),
                userUpdateDto.getFullname(),
                userUpdateDto.getEmail(),
                userUpdateDto.getPhone(),
                userUpdateDto.getPartnerId(),
                userUpdateDto.getCategoryId(),
                userUpdateDto.getChannelId(),
                id
        );
    }

    @Override
    public Optional<UserUpdateDto> getUserUpdateAtForm(String id) {
        String sql = """
        SELECT
                                      u.id,
                                      u.username,
                                      u.fullname,
                                      u.email,
                                      u.phone,
                                      u.partner_id,
                                      p.name AS name,
                                      u.category_id,
                                      c.name_category AS name_category,
                                      u.channel_id,
                                      ch.name_channel AS name_channel
                                  FROM tbl_user u
                                  LEFT JOIN tbl_partner_v2 p ON u.partner_id = p.id
                                  LEFT JOIN tbl_category c ON u.category_id = c.id
                                  LEFT JOIN tbl_channel ch ON u.channel_id = ch.id
                                  WHERE u.id = ?
    """;

        List<UserUpdateDto> users = jdbcTemplate.query(sql, (rs, rowNum) -> UserUpdateDto.builder()
                .id(rs.getLong("id"))
                .username(rs.getString("username"))
                .fullname(rs.getString("fullname"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .partnerId(rs.getLong("partner_id"))
                .partnerName(rs.getString("name"))
                .categoryId(rs.getLong("category_id"))
                .categoryName(rs.getString("name_category"))
                .channelId(rs.getLong("channel_id"))
                .channelName(rs.getString("name_channel"))
                .build(), id);

        return users.stream().findFirst();
    }


    @Override
    public void delete(String id) {
        String deleteUserRole = "DELETE FROM tbl_user_role WHERE user_id = ?";
        jdbcTemplate.update(deleteUserRole, id);

        String deleteUser = "DELETE FROM tbl_user WHERE id = ?";
        jdbcTemplate.update(deleteUser, id);
    }


    @Override
    public List<UserDto> filterUsersWithPagination(String name, String email, String phone, int offset, int size) {
        StringBuilder sql = new StringBuilder("""
        SELECT 
            u.id,
            u.fullname,
            u.email,
            u.phone,
            p.name AS partnerName,
            GROUP_CONCAT(r.role_name SEPARATOR ', ') AS roleName,
            u.created_at,
            u.updated_at
        FROM tbl_user u
        LEFT JOIN tbl_partner_v2 p ON u.partner_id = p.id
        LEFT JOIN tbl_user_role ur ON u.id = ur.user_id
        LEFT JOIN tbl_role r ON ur.role_id = r.id
        WHERE 1=1
    """);

        List<Object> params = new ArrayList<>();
        if (name != null && !name.isEmpty()) {
            sql.append(" AND u.fullname LIKE ?");
            params.add("%" + name + "%");
        }
        if (email != null && !email.isEmpty()) {
            sql.append(" AND u.email LIKE ?");
            params.add("%" + email + "%");
        }
        if (phone != null && !phone.isEmpty()) {
            sql.append(" AND u.phone LIKE ?");
            params.add("%" + phone + "%");
        }

        sql.append(" GROUP BY u.id ORDER BY u.created_at DESC LIMIT ? OFFSET ?");
        params.add(size);
        params.add(offset);

        return jdbcTemplate.query(sql.toString(), params.toArray(), (rs, rowNum) -> {
            UserDto dto = new UserDto();
            dto.setId(rs.getLong("id"));
            dto.setFullname(rs.getString("fullname"));
            dto.setEmail(rs.getString("email"));
            dto.setPhone(rs.getString("phone"));
            dto.setPartnerName(rs.getString("partnerName"));
            dto.setRoleName(rs.getString("roleName")); // danh sách role nối lại
            dto.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            dto.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return dto;
        });
    }


    @Override
    public int countUsersWithFilter(String name, String email, String phone) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM tbl_user u WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            sql.append(" AND u.fullname LIKE ?");
            params.add("%" + name + "%");
        }
        if (email != null && !email.isEmpty()) {
            sql.append(" AND u.email LIKE ?");
            params.add("%" + email + "%");
        }
        if (phone != null && !phone.isEmpty()) {
            sql.append(" AND u.phone LIKE ?");
            params.add("%" + phone + "%");
        }

        return jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Integer.class);
    }


    @Override
    public void updatePassword(String userId, String encodedPassword) {
        String sql = "UPDATE tbl_user SET password = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, encodedPassword, LocalDateTime.now(), userId);
    }

}
