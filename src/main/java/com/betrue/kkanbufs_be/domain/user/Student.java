package com.betrue.kkanbufs_be.domain.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@DiscriminatorValue("S")
@SuperBuilder
@NoArgsConstructor
public class Student extends User {
    private String studentUnm;

    private String college;

    private String phoneNum;

    private boolean sex;

    private boolean identified;

    public Student(String name, String loginId, String password, String studentUnm, String college, String phoneNum,boolean sex) {
        super(name, loginId, password);
        this.studentUnm = studentUnm;
        this.college = college;
        this.phoneNum = phoneNum;
        this.sex = sex;
        this.identified = true;
    }
}
      