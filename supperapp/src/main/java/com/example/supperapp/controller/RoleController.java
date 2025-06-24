package com.example.supperapp.controller;

import com.example.supperapp.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService){this.roleService=roleService;}

    @PostMapping("/sys/user-role/create")
    public String addRolesToUser(@RequestParam String id, @RequestParam("role1") List<String> roles) {
        roleService.addRoles(id, roles);
        return "redirect:/admin/user/view/" + id;
    }

    @PostMapping("/sys/user-role/delete")
    public String removeRolesFromUser(@RequestParam String id, @RequestParam("role2") List<String> roles) {
        roleService.removeRoles(id, roles);
        return "redirect:/admin/user/view/" + id;
    }

}
