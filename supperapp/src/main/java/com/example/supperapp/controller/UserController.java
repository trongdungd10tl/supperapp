package com.example.supperapp.controller;

import com.example.supperapp.dto.UserDto;
import com.example.supperapp.dto.UserUpdateDto;
import com.example.supperapp.response.ApiResponse;
import com.example.supperapp.model.User;
import com.example.supperapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> userDto = userService.getAllUser();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách thành công", userDto));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable String id) {
        return userService.getUserById(id)
                .map(userDto -> ResponseEntity.ok(ApiResponse.success("Tìm thấy người dùng", userDto)))
                .orElse(ResponseEntity.status(404).body(ApiResponse.error(404, "Không tìm thấy người dùng")));
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Void>> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(ApiResponse.success("Tạo người dùng thành công", null));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateUser(@PathVariable String id, @RequestBody UserUpdateDto userUpdateDto) {
        userService.updateUser(id, userUpdateDto);
        return ResponseEntity.ok(ApiResponse.success("Cập nhật người dùng thành công", null));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("Xóa người dùng thành công", null));
    }


}
