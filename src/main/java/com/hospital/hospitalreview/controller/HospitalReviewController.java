package com.hospital.hospitalreview.controller;

import com.hospital.hospitalreview.domain.HospitalReview;
import com.hospital.hospitalreview.domain.dto.HospitalReviewReadResponse;
import com.hospital.hospitalreview.service.HospitalReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hospitals/reviews")
@Slf4j
@RequiredArgsConstructor //필요한 argument를 constructor로 변경해줌
public class HospitalReviewController {

    private final HospitalReviewService hospitalReviewService;

    @GetMapping("/{id}")
    public ResponseEntity<HospitalReviewReadResponse> get(@PathVariable Long id){
        HospitalReview hospitalReview = hospitalReviewService.getReview(id);
        HospitalReviewReadResponse response = HospitalReviewReadResponse.fromEntity(hospitalReview);
        return ResponseEntity.ok().body(response);
    }
}
