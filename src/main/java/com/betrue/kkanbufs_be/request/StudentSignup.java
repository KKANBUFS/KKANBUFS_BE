package com.betrue.kkanbufs_be.request;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
public class StudentSignup extends Signup {

    private final String studentUnm;

    private final String college;

    private final String dept;

    private final String phoneNum;

    private final boolean sex;

    private final boolean identified;

    public StudentSignup(String name, String loginId, String password, String studentUnm, String college, String dept, String phoneNum, boolean sex) {
        super(name, loginId, password);
        this.studentUnm = studentUnm;
        this.college = college;
        this.dept = dept;
        this.phoneNum = phoneNum;
        this.sex = sex;
        this.identified = true;
    }
}
