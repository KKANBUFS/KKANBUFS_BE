package com.betrue.kkanbufs_be.response.user;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class StudentResponse extends UserResponse {
    private final String studentUnm;

    private final String college;

    private final String phoneNum;

    private final String sex;

    private final String identified;

    public StudentResponse(String name, String loginId, String studentUnm, String college, String phoneNum, String sex, String identified) {
        super(name, loginId);
        this.studentUnm = studentUnm;
        this.college = college;
        this.phoneNum = phoneNum;
        this.sex = sex;
        this.identified = identified;
    }
}
