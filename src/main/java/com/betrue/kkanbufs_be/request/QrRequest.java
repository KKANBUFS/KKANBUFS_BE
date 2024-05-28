package com.betrue.kkanbufs_be.request;


import lombok.Builder;
import lombok.Getter;

@Getter
public class QrRequest {
    private final String loginId;
    private final String college;
    private final String studentNum;

    @Builder
    public QrRequest(String loginId, String college, String studentNum) {
        this.loginId = loginId;
        this.college = college;
        this.studentNum = studentNum;
    }
}
