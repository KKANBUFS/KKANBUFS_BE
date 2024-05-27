package com.betrue.kkanbufs_be.domain;

import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.Partner;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class PartnerShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private Partner partner;

    @ManyToOne
    private College college;

    @Builder
    public PartnerShip(College college, Partner partner) {
        this.college = college;
        this.partner = partner;
    }
}
