package com.betrue.kkanbufs_be.domain.user;

import com.betrue.kkanbufs_be.domain.PartnerShip;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DiscriminatorValue("C")
@SuperBuilder
@NoArgsConstructor
public class College extends User {

    private String instagram;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "college")
    private List<PartnerShip> partnerShips = new ArrayList<>();

    public College(String name, String loginId, String password, String instagram) {
        super(name, loginId, password);
        this.instagram = instagram;
    }

    public void addPartnerShip(PartnerShip partnerShip) {
        partnerShips.add(partnerShip);
    }
}
