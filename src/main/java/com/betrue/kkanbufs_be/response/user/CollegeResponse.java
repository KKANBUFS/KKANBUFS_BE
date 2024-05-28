package com.betrue.kkanbufs_be.response.user;

import com.betrue.kkanbufs_be.domain.PartnerShip;
import com.betrue.kkanbufs_be.domain.user.College;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class CollegeResponse extends UserResponse {
    private String instagram;

    private List<PartnerShip> partnerShips;

    public CollegeResponse(String name, String loginId, String instagram, List<PartnerShip> partnerShips) {
        super(name, loginId);
        this.instagram = instagram;
        this.partnerShips = partnerShips;
    }
}
