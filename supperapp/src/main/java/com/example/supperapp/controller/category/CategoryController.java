package com.example.supperapp.controller.category;

import com.example.supperapp.dto.CategoryDto;
import com.example.supperapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/user")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/create/ajax-category-search")
    public List<Map<String, Object>> searchCategory(@RequestParam(value = "input_", required = false) String keyword) {
        List<CategoryDto> categories = (keyword == null || keyword.isBlank())
                ? categoryService.getAllCategories()
                : categoryService.searchByKeyword(keyword);

        return categories.stream().map(c -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", c.getId());
            map.put("text", c.getNameCategory()); // key "text" để Select2 hiểu
            return map;
        }).collect(Collectors.toList());
    }
}
