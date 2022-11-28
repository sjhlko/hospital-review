package com.hospital.hospitalreview.service;

import com.hospital.hospitalreview.domain.User;
import com.hospital.hospitalreview.domain.dto.UserDto;
import com.hospital.hospitalreview.domain.dto.UserJoinRequest;
import com.hospital.hospitalreview.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public UserDto join(UserJoinRequest request){

        //유저아이디 중복처리 ->  예외처리
//        userRepository.findByUserName(request.getUserName())
//                .orElseThrow(()->new RuntimeException("유저 중복"));
        //있으면 에러처리해야함 위는 없으면 에러처리

        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new RuntimeException("아이디 중복");
                });

        User savedUser = userRepository.save(request.toEntity());
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmailAddress())
                .build();
    }
}
