package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.domain.PartnerShip;
import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.domain.user.Partner;
import com.betrue.kkanbufs_be.domain.user.Student;
import com.betrue.kkanbufs_be.domain.user.User;
import com.betrue.kkanbufs_be.exception.PartnerShipNotFound;
import com.betrue.kkanbufs_be.exception.UnChecked;
import com.betrue.kkanbufs_be.exception.Unauthorized;
import com.betrue.kkanbufs_be.exception.UserNotFound;
import com.betrue.kkanbufs_be.repository.PartnerShipRepository;
import com.betrue.kkanbufs_be.repository.user.CollegeRepository;
import com.betrue.kkanbufs_be.repository.user.PartnerRepository;
import com.betrue.kkanbufs_be.request.PartnerSignup;
import com.betrue.kkanbufs_be.request.QrRequest;
import com.betrue.kkanbufs_be.response.PartnerShipResponse;
import com.betrue.kkanbufs_be.response.user.PartnerResponse;
import com.betrue.kkanbufs_be.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {

    private final AuthService authService;

    private final PartnerRepository partnerRepository;

    private final CollegeRepository collegeRepository;

    private final PartnerShipRepository partnerShipRepository;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid PartnerSignup signup){
        authService.signup(signup);
    }

    @GetMapping("/my")
    public PartnerResponse get(NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null || session.equals("")) {
            throw new Unauthorized();
        }

        User user = session.getUser();

        Partner partner = partnerRepository.findByLoginId(user.getLoginId()).orElseThrow(UserNotFound::new);

        return PartnerResponse.builder()
                .name(partner.getName())
                .loginId(partner.getLoginId())
                .partnerNum(partner.getPartnerNum())
                .partnerType(partner.getPartnerType())
                .city(partner.getCity())
                .address1(partner.getAddress1())
                .address2(partner.getAddress2())
                .build();
    }

    @PostMapping("/qr")
    public void qr(@RequestBody QrRequest qrRequest, NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null || session.equals("")) {
            throw new Unauthorized();
        }

        Student student = authService.findByLoginId(qrRequest.getLoginId());
        if(student.getCollege().equals(qrRequest.getCollege())) {

            PartnerShip partnerShip = partnerShipRepository.findByPartnerAndCollege(
                    partnerRepository.findByLoginId(session.getUser().getLoginId()).orElseThrow(UnChecked::new),
                    collegeRepository.findByName(student.getCollege()).orElseThrow(UnChecked::new)).orElseThrow(PartnerShipNotFound::new);


            PartnerShipResponse.builder()
                    .college(partnerShip.getCollege())
                    .benefit(partnerShip.getBenefit())
                    .partner(partnerShip.getPartner())
                    .build();
        }else throw new UnChecked();
    }
}
