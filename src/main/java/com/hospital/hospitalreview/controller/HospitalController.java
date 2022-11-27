package com.hospital.hospitalreview.controller;

import com.hospital.hospitalreview.domain.dto.ReviewCreateRequest;
import com.hospital.hospitalreview.domain.dto.ReviewCreateResponse;
import com.hospital.hospitalreview.domain.dto.ReviewReadResponse;
import com.hospital.hospitalreview.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
@Slf4j
public class HospitalController {

    private final ReviewService reviewService;

    public HospitalController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> add(@RequestBody ReviewCreateRequest reviewCreateRequest){
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }

    @GetMapping("{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> reviews(@PathVariable Long hospitalId){
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(hospitalId));
    }




}
