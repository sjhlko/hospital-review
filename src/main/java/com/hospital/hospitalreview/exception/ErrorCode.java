package com.hospital.hospitalreview.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT,"User name duplicated"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "user doesn't exist"),

    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "password is not matched")
    ;
    private HttpStatus status;
    private String message;
}
