package com.betrue.kkanbufs_be.request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FindPw {
    private String LoginId;
    private String studentNum;
}
