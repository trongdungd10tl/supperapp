package com.example.supperapp.controller;

import com.example.supperapp.dao.RoleDao;
import com.example.supperapp.dto.RoleDto;
import com.example.supperapp.service.RoleService;
import com.example.supperapp.service.UserService;
import com.example.supperapp.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/add-role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final RoleDao roleDao;
    private final UserService userService; // Để lấy thông tin user (fullname, email, ...)

    @GetMapping("/user/{userId}")
    public String showUserRoles(@PathVariable String userId, Model model) {
        Optional<UserDto> userOptional = userService.getUserById(userId);
        if (userOptional.isEmpty()) {
            return "redirect:/admin/user?error=notfound";
        }

        UserDto user = userOptional.get();
        List<RoleDto> assignedRoles = roleDao.findRolesByUserId(userId);
        List<RoleDto> allRoles = roleDao.findAll();

        // Các role chưa gán
        List<RoleDto> availableRoles = allRoles.stream()
                .filter(role -> assignedRoles.stream().noneMatch(a -> a.getId().equals(role.getId())))
                .collect(Collectors.toList());

        model.addAttribute("user", user);
        model.addAttribute("assignedRoles", assignedRoles);
        model.addAttribute("availableRoles", availableRoles);
        return "user/user-add-role";
    }

    @PostMapping("/create")
    public String addRolesToUser(@RequestParam String id, @RequestParam(name = "role1") List<String> roleIds) {
        roleService.addRoles(id, roleIds);
        return "redirect:/admin/add-role/user/" + id;
    }

    @PostMapping("/delete")
    public String removeRolesFromUser(@RequestParam String id, @RequestParam(name = "role2") List<String> roleIds) {
        roleService.removeRoles(id, roleIds);
        return "redirect:/admin/add-role/user/" + id;
    }
}
