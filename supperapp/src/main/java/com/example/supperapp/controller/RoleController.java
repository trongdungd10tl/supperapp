package com.example.supperapp.controller;

import com.example.supperapp.model.Role;
import com.example.supperapp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    // GET: Lấy danh sách role có filter và phân trang
    @GetMapping
    public List<Role> getRoles(
            @RequestParam(required = false) String role_name,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return roleService.filterRolesWithPagination(role_name, description, page, size);
    }

    // GET: Đếm số lượng role khớp filter
    @GetMapping("/count")
    public int countRoles(
            @RequestParam(required = false) String role_name,
            @RequestParam(required = false) String description
    ) {
        return roleService.countRolesWithFilter(role_name, description);
    }

    // GET: Lấy role theo id (dùng cho sửa)
    @GetMapping("/{id}")
    public Optional<Role> getRoleById(@PathVariable String id) {
        return roleService.getRoleUpdateAtForm(id);
    }

    // POST: Tạo mới role
    @PostMapping
    public String createRole(@RequestBody Role role) {
        roleService.createRole(role);
        return "Role created successfully";
    }

    // PUT: Cập nhật role
    @PutMapping("/{id}")
    public String updateRole(@PathVariable String id, @RequestBody Role role) {
        roleService.updateRole(id, role);
        return "Role updated successfully";
    }

    // DELETE: Xóa role
    @DeleteMapping("/{id}")
    public String deleteRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return "Role deleted successfully";
    }
}
