package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.request.PartnerShipCreate;
import com.betrue.kkanbufs_be.service.AuthService;
import com.betrue.kkanbufs_be.service.PartnerShipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CollegeController {
    private final PartnerShipService partnerShipService;

    private final AuthService authService;

    @PostMapping("/college/connection")
    public void connection(@RequestBody PartnerShipCreate partnerShipCreate, NativeWebRequest webRequest) {
        //todo college user 세션 확인후
        Session session = authService.getSession(webRequest);

        partnerShipService.connection(partnerShipCreate,session);
    }
}
