package com.betrue.kkanbufs_be.service;

import com.betrue.kkanbufs_be.domain.PartnerShip;
import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.Partner;
import com.betrue.kkanbufs_be.exception.PostNotFound;
import com.betrue.kkanbufs_be.repository.*;
import com.betrue.kkanbufs_be.repository.user.CollegeRepository;
import com.betrue.kkanbufs_be.repository.user.PartnerRepository;
import com.betrue.kkanbufs_be.request.PartnerShipCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class PartnerShipService {

    private final PartnerShipRepository partnerShipRepository;

    private final PartnerRepository partnerRepository;

    public void connection(PartnerShipCreate partnerShipCreate, Session session){
        Partner partner = partnerRepository.findByLoginId(partnerShipCreate.getPartnerId()).orElseThrow(PostNotFound::new);
        College college = (College) session.getUser();

        PartnerShip partnerShip = PartnerShip.builder()
                .partner(partner)
                .college(college)
                .benefit(partnerShipCreate.getBenefit())
                .build();

        college.addPartnerShip(partnerShip);

        partnerShipRepository.save(partnerShip);
    }
    
}
