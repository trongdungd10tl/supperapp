package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.ReviewerDao;
import com.example.supperapp.model.Reviewer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewerDaoImpl implements ReviewerDao {
    private final JdbcTemplate jdbcTemplate;


    public ReviewerDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public List<Reviewer> findAll(){
        String sql = """
        SELECT
            r.id,
            r.msisdn,
            r.`fullName`,
            r.created_at,
            u.username AS created_by
        FROM tbl_reviewer r
        LEFT JOIN tbl_user u ON r.created_by = u.id
        """;

        return jdbcTemplate.query(sql,(rs, rowNum) -> Reviewer.builder()
                .id(rs.getInt("id"))
                .msisdn(rs.getString("msisdn"))
                .fullname(rs.getString("fullName"))
                .created_at(rs.getTimestamp("created_at").toLocalDateTime())
                .created_by(rs.getString("created_by"))
                .build());
    }
}
