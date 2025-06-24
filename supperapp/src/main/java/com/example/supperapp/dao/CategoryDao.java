package com.example.supperapp.dao;

import com.example.supperapp.dto.CategoryDto;

import java.util.List;

public interface CategoryDao {
    List<CategoryDto> findAll();
    List<CategoryDto> searchByKeyword(String keyword);
}
