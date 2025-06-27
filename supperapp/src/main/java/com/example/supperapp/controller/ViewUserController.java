package com.example.supperapp.controller;

import com.example.supperapp.dto.UserDto;
import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.model.User;
import com.example.supperapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/user")
public class ViewUserController {

    private final UserService userService;

    public ViewUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String redirectToDefaultSearch() {
        return "redirect:/admin/user/list?page=1&size=10";
    }

    @PostMapping("/resetpassword")
    public String resetPassword(
            @RequestParam("userId") String userId,
            @RequestParam("passwordnew") String password,
            @RequestParam("passwordnew2") String confirmPassword,
            RedirectAttributes redirectAttributes) {

        if (!password.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Mật khẩu xác nhận không khớp!");
            return "redirect:/admin/user";
        }

        try {
            userService.updatePassword(userId, password);
            redirectAttributes.addFlashAttribute("successMessage", "Đổi mật khẩu thành công!");
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Lỗi khi cập nhật mật khẩu!");
        }

        return "redirect:/admin/user";
    }



    @GetMapping("/update/{id}")
    public String getUserUpdateAtForm(@PathVariable String id, Model model) {
        UserUpdateDto userUpdateDto = userService.getUserUpdateAtForm(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user với id = " + id));

        model.addAttribute("userUpdateDto", userUpdateDto);
        return "user/user-update";
    }



    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute UserUpdateDto userUpdateDto) {
        userService.updateUser(id, userUpdateDto);
        return "redirect:/admin/user";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/user-create";
    }


    @PostMapping("/create")
    public String createUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/admin/user";
    }


    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }


    @GetMapping("/list")
    public String searchWithPagination(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        // Tổng số user sau khi lọc
        int totalUsers = userService.countUsersWithFilter(name, email, phone);

        // Lấy danh sách user sau khi lọc và phân trang
        List<UserDto> users = userService.filterUsersWithPagination(name, email, phone, page, size);

        int totalPages = (int) Math.ceil((double) totalUsers / size);

        // Truyền dữ liệu ra view
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalUsers", totalUsers);

        // Giữ lại filter để hiển thị lại input trong form
        model.addAttribute("name", name);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);

        return "/user/user";
    }


}