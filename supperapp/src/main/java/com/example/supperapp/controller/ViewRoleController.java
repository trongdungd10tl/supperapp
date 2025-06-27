package com.example.supperapp.controller;

import com.example.supperapp.dao.RoleDao;
import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.model.Role;
import com.example.supperapp.model.User;
import com.example.supperapp.service.RoleService;
import com.example.supperapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/role")
public class ViewRoleController {

    private final RoleService roleService;

    public ViewRoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public String redirectToDefaultSearch() {
        return "redirect:/admin/role/list?page=1&size=10";
    }

    @GetMapping("list")
    public String searchWithPagination(
            @RequestParam(required = false) String roleName,
            @RequestParam(required = false) String description,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        // Tổng số role sau khi lọc
        int totalRoles = roleService.countRolesWithFilter(roleName, description);

        // Lấy danh sách role sau khi lọc và phân trang
        List<Role> roles = roleService.filterRolesWithPagination(roleName, description, page, size);

        int totalPages = (int) Math.ceil((double) totalRoles / size);

        model.addAttribute("roles", roles);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalRoles", totalRoles);

        // Giữ lại filter để hiển thị lại input trong form
        model.addAttribute("roleName", roleName);
        model.addAttribute("description", description);

        return "role/role";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("role", new Role());
        return "/role/role-create";
    }


    @PostMapping("/create")
    public String createUser(@ModelAttribute Role role, RedirectAttributes redirectAttributes) {
        try {
            roleService.createRole(role);
            redirectAttributes.addFlashAttribute("successMessage", "Tạo role thành công");
        } catch (DuplicateKeyException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Tên role đã tồn tại");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Đã có lỗi xảy ra khi tạo role");
        }
        return "redirect:/admin/role/create"; // trở lại form tạo
    }


    @PostMapping("/delete/{id}")
    public String deleteRole(@PathVariable String id, RedirectAttributes redirectAttributes) {
        roleService.deleteRole(id);
        redirectAttributes.addFlashAttribute("success", "Delete role successfully!");
        return "redirect:/admin/role";
    }


    @GetMapping("/update/{id}")
    public String getRoleUpdateAtForm(@PathVariable String id, Model model) {
        Role role = roleService.getRoleUpdateAtForm(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user với id = " + id));
        model.addAttribute("role", role);
        return "role/role-update";
    }



    @PostMapping("/update/{id}")
    public String updateRole(@PathVariable String id, @ModelAttribute Role role) {
        roleService.updateRole(id, role);
        return "redirect:/admin/role";
    }



}
