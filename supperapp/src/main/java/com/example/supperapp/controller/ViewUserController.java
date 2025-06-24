package com.example.supperapp.controller;

import com.example.supperapp.dto.UserDto;
import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.model.User;
import com.example.supperapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping
//    public String listUsers(Model model) {
//        List<UserDto> users = userService.getAllUser();
//        model.addAttribute("users", users);
//        return "user";
//    }


    @GetMapping("/update/{id}")
    public String getUserUpdateAtForm(@PathVariable String id, Model model) {
        // Lấy ra đối tượng
        UserUpdateDto dto = userService.getUserUpdateAtForm(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy user với id = " + id));

        // Truyền dto vào Thymeleaf
        model.addAttribute("userUpdateDto", dto);
        return "user/user-update"; // Trả về trang update
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


//    @GetMapping("/view")
//    public String AddRole(){
//        return "User-add-role";
//    }


}
