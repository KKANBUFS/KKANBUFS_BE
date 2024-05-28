package com.betrue.kkanbufs_be.domain.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("S")
@SuperBuilder
@NoArgsConstructor
public class Student extends User {
    private String studentUnm;

    private String college;

    private String dept;

    private String phoneNum;

    private boolean identified;

    public Student(String name, String loginId, String password, String studentUnm, String college, String dept, String phoneNum) {
        super(name, loginId, password);
        this.studentUnm = studentUnm;
        this.college = college;
        this.dept = dept;
        this.phoneNum = phoneNum;
        this.identified = true;
    }
}
