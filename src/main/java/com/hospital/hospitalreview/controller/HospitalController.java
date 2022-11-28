package com.hospital.hospitalreview.controller;

import com.hospital.hospitalreview.domain.Hospital;
import com.hospital.hospitalreview.domain.dto.HospitalReadResponse;
import com.hospital.hospitalreview.domain.dto.ReviewCreateRequest;
import com.hospital.hospitalreview.domain.dto.ReviewCreateResponse;
import com.hospital.hospitalreview.domain.dto.ReviewReadResponse;
import com.hospital.hospitalreview.service.HospitalService;
import com.hospital.hospitalreview.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final ReviewService reviewService;
    private final HospitalService hospitalService;

    @PostMapping("{id}/reviews")
    public ResponseEntity<ReviewCreateResponse> add(@RequestBody ReviewCreateRequest reviewCreateRequest){
        return ResponseEntity.ok().body(reviewService.createReview(reviewCreateRequest));
    }

    @GetMapping("{hospitalId}/reviews")
    public ResponseEntity<List<ReviewReadResponse>> reviews(@PathVariable Long hospitalId){
        return ResponseEntity.ok().body(reviewService.findAllByHospitalId(hospitalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalReadResponse> hospital(@PathVariable Long id){
        Hospital hospital = hospitalService.findById(id);
        HospitalReadResponse hospitalReadResponse = HospitalReadResponse.fromEntity(hospital);
        return ResponseEntity.ok().body(hospitalReadResponse);
    }


}
