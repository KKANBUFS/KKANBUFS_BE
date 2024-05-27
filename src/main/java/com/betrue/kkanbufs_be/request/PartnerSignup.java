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
public class PartnerSignup extends Signup{

    private String partnerNum;

    private String partnerType;

    private String address1; //도로명

    private String address2; //상새주소

    private String city;

    public PartnerSignup(String name, String loginId, String password, String partnerNum, String partnerType, String address1, String address2, String city) {
        super(name, loginId, password);
        this.partnerNum = partnerNum;
        this.partnerType = partnerType;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
    }
}
