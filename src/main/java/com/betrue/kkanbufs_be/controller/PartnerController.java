package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.request.PartnerSignup;
import com.betrue.kkanbufs_be.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/partner")
public class PartnerController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid PartnerSignup signup){
        authService.signup(signup);
    }
}
