package com.hospital.hospitalreview.domain.dto;

import com.hospital.hospitalreview.domain.HospitalReview;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HospitalReviewReadResponse {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private String hospitalName;

    public static HospitalReviewReadResponse fromEntity(HospitalReview hospitalReview){
        HospitalReviewReadResponse response = HospitalReviewReadResponse.builder()
                .id(hospitalReview.getId())
                .title(hospitalReview.getTitle())
                .content(hospitalReview.getContent())
                .userName(hospitalReview.getUserName())
                .hospitalName(hospitalReview.getHospital().getHospitalName())
                .build();
        return response;
    }
}
