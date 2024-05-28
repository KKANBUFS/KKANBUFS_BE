package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.request.FindId;
import com.betrue.kkanbufs_be.request.FindPw;
import com.betrue.kkanbufs_be.request.StudentSignup;
import com.betrue.kkanbufs_be.response.LoginIdResponse;
import com.betrue.kkanbufs_be.response.PwResponse;
import com.betrue.kkanbufs_be.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final AuthService authService;


    @PostMapping("/signup")
    public void signup(@RequestBody @Valid StudentSignup signup){
        authService.signup(signup);
    }


    @GetMapping("/findid")
    public LoginIdResponse findId(@RequestBody FindId findId) {
        return authService.findLoginId(findId);
    }

    @GetMapping("/findpw")
    public PwResponse findPw(@RequestBody FindPw findPW) {
        return authService.findPw(findPW);
    }
}
