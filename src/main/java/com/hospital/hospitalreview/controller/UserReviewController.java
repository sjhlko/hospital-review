package com.hospital.hospitalreview.controller;

import com.hospital.hospitalreview.domain.dto.ReviewCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-reviews")
public class UserReviewController {

    @PostMapping
    public String write(@RequestBody ReviewCreateRequest dto){
        return "리뷰 등록 성공";
    }
}
