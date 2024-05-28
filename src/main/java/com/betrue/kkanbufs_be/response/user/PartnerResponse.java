package com.betrue.kkanbufs_be.response.user;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class PartnerResponse extends UserResponse {
    private String partnerNum;

    private String partnerType;

    private String address1; //도로명

    private String address2; //상새주소

    private String city;

    public PartnerResponse(String name, String loginId, String partnerNum, String partnerType, String address1, String address2, String city) {
        super(name, loginId);
        this.partnerNum = partnerNum;
        this.partnerType = partnerType;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
    }
}
