package com.betrue.kkanbufs_be.response;

import lombok.Builder;

public class QrResponse {
    private String loginId;
    private String college;
    private String studentNum;

    @Builder
    public QrResponse(String loginId, String college, String studentNum) {
        this.loginId = loginId;
        this.college = college;
        this.studentNum = studentNum;
    }
}
