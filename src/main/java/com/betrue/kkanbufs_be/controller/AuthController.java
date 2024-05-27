package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.request.*;
import com.betrue.kkanbufs_be.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth/login")
    public ResponseEntity<Object> signin(@RequestBody @Valid Login login) {
        String accessToken = authService.signin(login);
        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
                .domain("localhost") //todo 서버환경에따라 변경필요
                .path("/")
                .httpOnly(true)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict")
                .build();
        log.info(">>> cookie = {}",cookie);
        log.info(">>> cookie name = {}",cookie.getName());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @PostMapping("/auth/student/signup")
    public void signup(@RequestBody @Valid StudentSignup signup){
        authService.signup(signup);
    }
    @PostMapping("/auth/college/signup")
    public void signup(@RequestBody @Valid CollegeSignup signup){
        authService.signup(signup);
    }
    @PostMapping("/auth/partner/signup")
    public void signup(@RequestBody @Valid PartnerSignup signup){
        authService.signup(signup);
    }

}
