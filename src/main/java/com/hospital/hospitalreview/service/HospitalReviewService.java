package com.hospital.hospitalreview.service;

import com.hospital.hospitalreview.domain.Hospital;
import com.hospital.hospitalreview.domain.HospitalReview;
import com.hospital.hospitalreview.domain.dto.HospitalReviewCreateRequest;
import com.hospital.hospitalreview.domain.dto.HospitalReviewCreateResponse;
import com.hospital.hospitalreview.domain.dto.HospitalReviewReadResponse;
import com.hospital.hospitalreview.repository.HospitalRepository;
import com.hospital.hospitalreview.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HospitalReviewService {
    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public HospitalReviewService(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }

    public HospitalReviewCreateResponse createReview(HospitalReviewCreateRequest hospitalReviewCreateRequest){
        Optional<Hospital> hospital = hospitalRepository.findById(hospitalReviewCreateRequest.getHospitalId());
        HospitalReview hospitalReview = HospitalReview.builder()
                .title(hospitalReviewCreateRequest.getTitle())
                .content(hospitalReviewCreateRequest.getContent())
                .userName(hospitalReviewCreateRequest.getUserName())
                .hospital(hospital.get())
                .build();
        HospitalReview savedHospitalReview = reviewRepository.save(hospitalReview);
        return new HospitalReviewCreateResponse(savedHospitalReview.getId(), savedHospitalReview.getTitle(), savedHospitalReview.getContent(), savedHospitalReview.getContent(),
                "리뷰 등록이 성공 했습니다.");
    }

    public HospitalReview getReview(Long id) {
        HospitalReview hospitalReview = reviewRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 id가 없습니다"));
        return hospitalReview;
    }

    public List<HospitalReviewReadResponse> findAllByHospitalId(Long hospitalId){
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()->new RuntimeException("해당 id가 없습니다."));
        List<HospitalReviewReadResponse> reviews = reviewRepository.findByHospital(hospital)
                .stream().map(review -> HospitalReviewReadResponse.builder()
                        .id(review.getId())
                        .title(review.getTitle())
                        .content(review.getContent())
                        .userName(review.getHospital().getHospitalName())
                        .hospitalName(review.getHospital().getHospitalName())
                        .build()
                ).collect(Collectors.toList());
        return reviews;
    }
}
