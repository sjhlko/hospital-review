package com.hospital.hospitalreview.service;

import com.hospital.hospitalreview.domain.Hospital;
import com.hospital.hospitalreview.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public Hospital findById(Long id){
        Hospital hospital = hospitalRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("id 가 없습니다"));
        return hospital;
    }
}
