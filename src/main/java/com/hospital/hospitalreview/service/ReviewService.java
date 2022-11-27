package com.hospital.hospitalreview.service;

import com.hospital.hospitalreview.domain.Hospital;
import com.hospital.hospitalreview.domain.Review;
import com.hospital.hospitalreview.domain.dto.ReviewCreateRequest;
import com.hospital.hospitalreview.domain.dto.ReviewCreateResponse;
import com.hospital.hospitalreview.repository.HospitalRepository;
import com.hospital.hospitalreview.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
