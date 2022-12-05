package com.hospital.hospitalreview.controller;

import com.hospital.hospitalreview.domain.dto.ReviewCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user-reviews")
public class UserReviewController {

    @PostMapping
    public String write(@RequestBody ReviewCreateRequest dto, Authentication authentication){
        log.info("Controller userName : {}", authentication.getName());
        return "리뷰 등록 성공\n"+"유저 이름 :"+authentication.getName();
    }
}
