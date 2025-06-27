package com.example.supperapp.controller;

import com.example.supperapp.dao.UserRoleDao;
import com.example.supperapp.dto.UserRoleDto;
import com.example.supperapp.service.UserRoleService;
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
public class ViewUserRoleController {

    private final UserRoleService roleService;
    private final UserRoleDao userRoleDao;
    private final UserService userService; // Để lấy thông tin user (fullname, email, ...)

    @GetMapping("/user/{userId}")
    public String showUserRoles(@PathVariable String userId, Model model) {
        Optional<UserDto> userOptional = userService.getUserById(userId);
        if (userOptional.isEmpty()) {
            return "redirect:/admin/user?error=notfound";
        }

        UserDto user = userOptional.get();
        List<UserRoleDto> assignedRoles = userRoleDao.findRolesByUserId(userId);
        List<UserRoleDto> allRoles = userRoleDao.findAll();

        // Các role chưa gán
        List<UserRoleDto> availableRoles = allRoles.stream()
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
