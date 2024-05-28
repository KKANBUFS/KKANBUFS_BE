package com.betrue.kkanbufs_be.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginIdResponse {
    private String Loginid;
    @Builder
    public LoginIdResponse(String loginid) {
        Loginid = loginid;
    }
}
