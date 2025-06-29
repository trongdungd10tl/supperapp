package com.example.supperapp.service;

import com.example.supperapp.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    List<CategoryDto> searchByKeyword(String keyword);
}
