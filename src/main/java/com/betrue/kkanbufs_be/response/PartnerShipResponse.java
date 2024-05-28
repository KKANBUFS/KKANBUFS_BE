package com.betrue.kkanbufs_be.response;

import com.betrue.kkanbufs_be.domain.PartnerShip;
import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.Partner;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

/*
* 서비스 정책의 맞는 클래스
*/
@Getter
public class PartnerShipResponse {
    private Long id;

    private Partner partner;

    @JsonIgnore
    private College college;

    private String benefit;

    //생성자 오버로딩
    public PartnerShipResponse(PartnerShip partnerShip) {
        this.id = partnerShip.getId();
        this.partner = partnerShip.getPartner();
        this.college = partnerShip.getCollege();
        this.benefit = partnerShip.getBenefit();
    }

    @Builder
    public PartnerShipResponse(Partner partner, College college, String benefit) {
        this.partner = partner;
        this.college = college;
        this.benefit = benefit;
    }
}
