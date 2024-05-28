package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.request.CollegeSignup;
import com.betrue.kkanbufs_be.request.PartnerShipCreate;
import com.betrue.kkanbufs_be.service.AuthService;
import com.betrue.kkanbufs_be.service.PartnerShipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/college")
public class CollegeController {

    private final AuthService authService;

    private final PartnerShipService partnerShipService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid CollegeSignup signup){
        authService.signup(signup);
    }
    @PostMapping("/connection")//college 체킹
    public void connection(@RequestBody PartnerShipCreate partnerShipCreate, NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);

        partnerShipService.connection(partnerShipCreate,session);
    }
}
