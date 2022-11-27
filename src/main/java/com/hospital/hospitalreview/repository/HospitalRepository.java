package com.hospital.hospitalreview.repository;

import com.hospital.hospitalreview.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

}
