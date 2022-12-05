package com.hospital.hospitalreview.controller;

import com.hospital.hospitalreview.domain.Hospital;
import com.hospital.hospitalreview.domain.dto.HospitalReadResponse;
import com.hospital.hospitalreview.domain.dto.HospitalReviewCreateRequest;
import com.hospital.hospitalreview.domain.dto.HospitalReviewCreateResponse;
import com.hospital.hospitalreview.domain.dto.HospitalReviewReadResponse;
import com.hospital.hospitalreview.service.HospitalService;
import com.hospital.hospitalreview.service.HospitalReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalReviewService hospitalReviewService;
    private final HospitalService hospitalService;

    @PostMapping("{id}/reviews")
    public ResponseEntity<HospitalReviewCreateResponse> add(@RequestBody HospitalReviewCreateRequest hospitalReviewCreateRequest){
        return ResponseEntity.ok().body(hospitalReviewService.createReview(hospitalReviewCreateRequest));
    }

    @GetMapping("{hospitalId}/reviews")
    public ResponseEntity<List<HospitalReviewReadResponse>> reviews(@PathVariable Long hospitalId){
        return ResponseEntity.ok().body(hospitalReviewService.findAllByHospitalId(hospitalId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalReadResponse> hospital(@PathVariable Long id){
        Hospital hospital = hospitalService.findById(id);
        HospitalReadResponse hospitalReadResponse = HospitalReadResponse.fromEntity(hospital);
        return ResponseEntity.ok().body(hospitalReadResponse);
    }


}
