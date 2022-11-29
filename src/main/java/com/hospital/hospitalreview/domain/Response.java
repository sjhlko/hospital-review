package com.hospital.hospitalreview.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    private String resultCode;
    private T result;

    public static Response<Void> error(String resultCode, String messaage){
        return new Response(resultCode, messaage);
    }
    public static <T> Response<T> success(T result){
        return new Response<>("SUCCESS", result);
    }
}
