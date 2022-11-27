package com.hospital.hospitalreview.repository;

import com.hospital.hospitalreview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
