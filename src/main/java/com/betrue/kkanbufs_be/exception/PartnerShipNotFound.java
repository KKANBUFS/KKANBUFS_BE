package com.betrue.kkanbufs_be.exception;

import org.hibernate.exception.DataException;

public class PartnerShipNotFound extends DailelogException {
    private static final String MESSAGE = "제휴 정보를 확인할 수 없습니다.";

    public PartnerShipNotFound() {
        super(MESSAGE);
    }


    @Override
    public int statusCode() {
        return 404;
    }
}
