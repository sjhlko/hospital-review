package com.hospital.hospitalreview.repository;

import com.hospital.hospitalreview.domain.Hospital;
import com.hospital.hospitalreview.domain.HospitalReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<HospitalReview, Long> {
    List<HospitalReview> findByHospital(Hospital hospital);
}
