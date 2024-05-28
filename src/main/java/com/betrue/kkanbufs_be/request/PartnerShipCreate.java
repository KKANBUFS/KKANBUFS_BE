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
    private final String partnerId;

    private final String benefit;

    @Builder
    public PartnerShipCreate(String partnerId, String benefit) {
        this.partnerId = partnerId;
        this.benefit = benefit;
    }

    public void validate() {

    }
}
