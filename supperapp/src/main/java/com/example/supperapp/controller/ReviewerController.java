package com.example.supperapp.controller;

import com.example.supperapp.model.Reviewer;
import com.example.supperapp.response.ApiResponse;
import com.example.supperapp.service.ReviewerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reviewer")
public class ReviewerController {

    private final ReviewerService reviewerService;

    public ReviewerController(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Reviewer>>> getAllReviewer(){
        List<Reviewer> reviewers = reviewerService.getAllReviewer();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách thành công",reviewers));
    }
}
