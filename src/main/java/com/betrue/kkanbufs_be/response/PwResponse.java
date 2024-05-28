package com.betrue.kkanbufs_be.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PwResponse {
    private String password;
    @Builder
    public PwResponse(String password) {
        this.password = password;
    }
}
