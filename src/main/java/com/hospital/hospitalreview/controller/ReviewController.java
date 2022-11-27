package com.hospital.hospitalreview.controller;

import com.hospital.hospitalreview.domain.Review;
import com.hospital.hospitalreview.domain.dto.ReviewReadResponse;
import com.hospital.hospitalreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@Slf4j
@RequiredArgsConstructor //필요한 argument를 constructor로 변경해줌
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{id}")
    public ResponseEntity<ReviewReadResponse> get(@PathVariable Long id){
        Review review = reviewService.getReview(id);
        ReviewReadResponse response = ReviewReadResponse.builder()
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .userName(review.getUserName())
                .hospitalName(review.getHospital().getHospitalName())
                .build();
        return ResponseEntity.ok().body(response);
    }
}
