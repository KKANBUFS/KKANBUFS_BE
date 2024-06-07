package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.domain.user.Student;
import com.betrue.kkanbufs_be.domain.user.User;
import com.betrue.kkanbufs_be.exception.Unauthorized;
import com.betrue.kkanbufs_be.exception.UserNotFound;
import com.betrue.kkanbufs_be.repository.user.StudentRepository;
import com.betrue.kkanbufs_be.request.FindId;
import com.betrue.kkanbufs_be.request.FindPw;
import com.betrue.kkanbufs_be.request.StudentSignup;
import com.betrue.kkanbufs_be.response.LoginIdResponse;
import com.betrue.kkanbufs_be.response.PwResponse;
import com.betrue.kkanbufs_be.response.QrResponse;
import com.betrue.kkanbufs_be.response.user.StudentResponse;
import com.betrue.kkanbufs_be.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

@Slf4j
@RestController

@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final AuthService authService;

    private final StudentRepository studentRepository;

    @GetMapping("/my")
    public StudentResponse get(NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null || session.equals("")) {
            throw new Unauthorized();
        }

        User user = session.getUser();

        Student student = studentRepository.findByLoginId(user.getLoginId()).orElseThrow(UserNotFound::new);

        return StudentResponse.builder()
                .name(student.getName())
                .loginId(student.getLoginId())
                .studentUnm(student.getStudentUnm())
                .college(student.getCollege())
                .phoneNum(student.getPhoneNum())
                .sex(student.isSex() ? "남자" : "여자")
                .identified(student.isIdentified() ? "인증" : "비인증")
                .build();
    }

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid StudentSignup signup) {
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

    @GetMapping("/qr")
    public QrResponse qr(NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null || session.equals("")) {
            throw new Unauthorized();
        }

        Student student = studentRepository.findByLoginId(session.getUser().getLoginId())
                .orElseThrow(UserNotFound::new);

        return QrResponse.builder()
                .loginId(student.getLoginId())
                .studentNum(student.getStudentUnm())
                .college(student.getCollege())
                .build();
    }
}
