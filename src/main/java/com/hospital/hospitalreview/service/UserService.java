package com.hospital.hospitalreview.service;

import com.hospital.hospitalreview.domain.User;
import com.hospital.hospitalreview.domain.dto.UserDto;
import com.hospital.hospitalreview.domain.dto.UserJoinRequest;
import com.hospital.hospitalreview.exception.ErrorCode;
import com.hospital.hospitalreview.exception.HospitalReviewAppException;
import com.hospital.hospitalreview.repository.UserRepository;
import com.hospital.hospitalreview.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 20;

    public UserDto join(UserJoinRequest request){

        //유저아이디 중복처리 ->  예외처리
//        userRepository.findByUserName(request.getUserName())
//                .orElseThrow(()->new RuntimeException("유저 중복"));
        //있으면 에러처리해야함 위는 없으면 에러처리

        userRepository.findByUserName(request.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.DUPLICATED_USER_NAME, String.format("username:%s",request.getUserName()));
                });

        User savedUser = userRepository.save(request.toEntity(encoder.encode(request.getPassword())));
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmailAddress())
                .build();
    }

    public String login(String userName, String password) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(()-> new HospitalReviewAppException(ErrorCode.NOT_FOUND, String.format("%s를 찾을 수 없음",userName)));
        if(!encoder.matches(password,user.getPassword())){
            throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD, String.format("id%s가 비번 %s와 매치되지않음",userName,password));
        }

        return JwtTokenUtil.createToken(userName, secretKey, expireTimeMs);
    }
}
