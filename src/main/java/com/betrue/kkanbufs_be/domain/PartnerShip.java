package com.betrue.kkanbufs_be.domain;

import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.Partner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class PartnerShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    @ManyToOne
    @JoinColumn(name = "college_id")
    @JsonIgnore
    private College college;

    private String benefit;

    @Builder
    public PartnerShip(College college, Partner partner, String benefit) {
        this.college = college;
        this.partner = partner;
        this.benefit = benefit;
    }
}
