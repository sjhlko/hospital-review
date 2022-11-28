package com.hospital.hospitalreview.controller;

import com.hospital.hospitalreview.domain.Response;
import com.hospital.hospitalreview.domain.dto.UserDto;
import com.hospital.hospitalreview.domain.dto.UserJoinRequest;
import com.hospital.hospitalreview.domain.dto.UserJoinResponse;
import com.hospital.hospitalreview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinResponse> join(@RequestBody UserJoinRequest userJoinRequest){
        UserDto userDto = userService.join(userJoinRequest);
        return Response.success(new UserJoinResponse(userDto.getUserName(),userDto.getEmail()));
    }
}
