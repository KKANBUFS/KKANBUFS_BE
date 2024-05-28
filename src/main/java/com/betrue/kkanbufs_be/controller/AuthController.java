package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.exception.Unauthorized;
import com.betrue.kkanbufs_be.request.*;
import com.betrue.kkanbufs_be.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.Duration;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> signin(@RequestBody @Valid Login login) {
        String accessToken = authService.signin(login);
        ResponseCookie cookie = ResponseCookie.from("SESSION", accessToken)
                .domain("localhost") //todo 서버환경에따라 변경필요
                .path("/")
                .httpOnly(true)
                .maxAge(Duration.ofDays(30))
                .sameSite("Strict")
                .build();
        log.info(">>> cookie = {}", cookie);
        log.info(">>> cookie name = {}", cookie.getName());
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Object> logout(NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null || session.equals("")) {
            throw new Unauthorized();
        }

        authService.logout(session);


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, "")
                .build();
    }
}
