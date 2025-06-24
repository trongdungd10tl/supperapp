package com.example.supperapp.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    // Trang login
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Trả về file login.html
    }

    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        String username = authentication.getName(); // tên đăng nhập
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER");

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        return "home";
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }

    // Trang đăng nhập thất bại (nếu muốn tùy chỉnh)
    @GetMapping("/login-error")
    public String loginError() {
        return "login"; // Hiện lại trang login
    }
}
