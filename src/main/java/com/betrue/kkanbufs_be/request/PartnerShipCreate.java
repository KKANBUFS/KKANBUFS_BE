package com.betrue.kkanbufs_be.request;

import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.Partner;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class PartnerShipCreate {
    private String partnerId;

    private String collegeId;

    private String benefit;

    @Builder
    public PartnerShipCreate(String partnerId, String collegeId, String benefit) {
        this.partnerId = partnerId;
        this.collegeId = collegeId;
        this.benefit = benefit;
    }

    public void validate() {

    }
}
