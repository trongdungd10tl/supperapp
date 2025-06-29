package com.example.supperapp.dao.impl;

import com.example.supperapp.dao.CategoryDao;
import com.example.supperapp.dto.CategoryDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {

    private final JdbcTemplate jdbcTemplate;

    public CategoryDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CategoryDto> findAll() {
        String sql = "SELECT id, name_category FROM tbl_category";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CategoryDto category = new CategoryDto();
            category.setId(rs.getInt("id"));
            category.setNameCategory(rs.getString("name_category"));
            return category;
        });
    }

    @Override
    public List<CategoryDto> searchByKeyword(String keyword) {
        String sql = "SELECT id, name_category FROM tbl_category WHERE name_category LIKE ?";
        String likeKeyword = "%" + keyword + "%";
        return jdbcTemplate.query(sql, new Object[]{likeKeyword}, (rs, rowNum) -> {
            CategoryDto category = new CategoryDto();
            category.setId(rs.getInt("id"));
            category.setNameCategory(rs.getString("name_category"));
            return category;
        });
    }
}
