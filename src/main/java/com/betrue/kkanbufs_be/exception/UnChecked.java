package com.betrue.kkanbufs_be.exception;

public class UnChecked extends DailelogException{
    private static final String MESSAGE = "제휴 단대 학생이 아닙니다";

    public UnChecked() {
        super(MESSAGE);
    }


    @Override
    public int statusCode() {
        return 404;
    }
}
