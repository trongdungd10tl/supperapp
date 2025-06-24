package com.example.supperapp.service.impl;

import com.example.supperapp.dao.CategoryDao;
import com.example.supperapp.dto.CategoryDto;
import com.example.supperapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public List<CategoryDto> searchByKeyword(String keyword) {
        return categoryDao.searchByKeyword(keyword);
    }
}
