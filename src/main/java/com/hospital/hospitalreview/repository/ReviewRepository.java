package com.hospital.hospitalreview.repository;

import com.hospital.hospitalreview.domain.Hospital;
import com.hospital.hospitalreview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByHospital(Hospital hospital);
}
