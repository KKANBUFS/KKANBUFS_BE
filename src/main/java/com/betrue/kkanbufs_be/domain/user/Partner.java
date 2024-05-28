package com.betrue.kkanbufs_be.domain.user;


import com.betrue.kkanbufs_be.domain.PartnerShip;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("P")
@SuperBuilder
@NoArgsConstructor
public class Partner extends User {

    private String partnerNum;

    private String partnerType;

    private String address1; //도로명

    private String address2; //상새주소

    private String city;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "partner")
    private List<PartnerShip> partnerShips = new ArrayList<>();

    public Partner(String name, String loginId, String password, String partnerNum, String partnerType, String address1, String address2, String city) {
        super(name, loginId, password);
        this.partnerNum = partnerNum;
        this.partnerType = partnerType;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
    }
}
