package com.hospital.hospitalreview.service;

import com.hospital.hospitalreview.domain.Hospital;
import com.hospital.hospitalreview.domain.Review;
import com.hospital.hospitalreview.domain.dto.ReviewCreateRequest;
import com.hospital.hospitalreview.domain.dto.ReviewCreateResponse;
import com.hospital.hospitalreview.domain.dto.ReviewReadResponse;
import com.hospital.hospitalreview.repository.HospitalRepository;
import com.hospital.hospitalreview.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final HospitalRepository hospitalRepository;
    private final ReviewRepository reviewRepository;

    public ReviewService(HospitalRepository hospitalRepository, ReviewRepository reviewRepository) {
        this.hospitalRepository = hospitalRepository;
        this.reviewRepository = reviewRepository;
    }

    public ReviewCreateResponse createReview(ReviewCreateRequest reviewCreateRequest){
        Optional<Hospital> hospital = hospitalRepository.findById(reviewCreateRequest.getHospitalId());
        Review review = Review.builder()
                .title(reviewCreateRequest.getTitle())
                .content(reviewCreateRequest.getContent())
                .userName(reviewCreateRequest.getUserName())
                .hospital(hospital.get())
                .build();
        Review savedReview = reviewRepository.save(review);
        return new ReviewCreateResponse(savedReview.getId(), savedReview.getTitle(), savedReview.getContent(), savedReview.getContent(),
                "리뷰 등록이 성공 했습니다.");
    }

    public Review getReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("해당 id가 없습니다"));
        return review;
    }

    public List<ReviewReadResponse> findAllByHospitalId(Long hospitalId){
        Hospital hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(()->new RuntimeException("해당 id가 없습니다."));
        List<ReviewReadResponse> reviews = reviewRepository.findByHospital(hospital)
                .stream().map(review -> ReviewReadResponse.builder()
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
