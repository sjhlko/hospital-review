package com.hospital.hospitalreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HospitalReviewCreateResponse {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private String message;
}