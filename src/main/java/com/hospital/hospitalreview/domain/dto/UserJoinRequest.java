package com.hospital.hospitalreview.domain.dto;

import com.hospital.hospitalreview.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public User toEntity(){
        return User.builder()
                .userName(this.userName)
                .password(this.password)
                .emailAddress(this.email)
                .build();
    }
}