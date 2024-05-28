package com.betrue.kkanbufs_be.response.user;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class UserResponse {

    private String name;

    private String loginId; // 아이디

    public UserResponse(String name, String loginId) {
        this.name = name;
        this.loginId = loginId;
    }
}
