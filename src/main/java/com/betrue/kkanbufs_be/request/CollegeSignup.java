package com.betrue.kkanbufs_be.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
public class CollegeSignup extends Signup {

    private String instagram;

    public CollegeSignup(String name, String loginId, String password, String instagram) {
        super(name, loginId, password);
        this.instagram = instagram;
    }
}
