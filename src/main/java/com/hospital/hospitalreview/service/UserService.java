package com.hospital.hospitalreview.service;

import com.hospital.hospitalreview.domain.dto.UserDto;
import com.hospital.hospitalreview.domain.dto.UserJoinRequest;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto join(UserJoinRequest request){
        return new UserDto(request.getUserName(), request.getPassword(), request.getEmail());
    }
}
