package com.hospital.hospitalreview.domain.dto;

import com.hospital.hospitalreview.domain.User;
import com.hospital.hospitalreview.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password){
        return User.builder()
                .userName(this.userName)
                .password(password)
                .emailAddress(this.email)
                .role(UserRole.USER)
                .build();
    }
}
