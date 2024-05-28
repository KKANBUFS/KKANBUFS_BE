package com.betrue.kkanbufs_be.service;

import com.betrue.kkanbufs_be.domain.*;
import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.Partner;
import com.betrue.kkanbufs_be.domain.user.Student;
import com.betrue.kkanbufs_be.domain.user.User;
import com.betrue.kkanbufs_be.exception.AlreadyExistsEmailException;
import com.betrue.kkanbufs_be.exception.InvalidSinginInformation;
import com.betrue.kkanbufs_be.exception.Unauthorized;
import com.betrue.kkanbufs_be.exception.UserNotFound;
import com.betrue.kkanbufs_be.repository.SessionRepository;
import com.betrue.kkanbufs_be.repository.user.StudentRepository;
import com.betrue.kkanbufs_be.repository.user.UserRepository;
import com.betrue.kkanbufs_be.request.*;
import com.betrue.kkanbufs_be.response.LoginIdResponse;
import com.betrue.kkanbufs_be.response.PwResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    private final SessionRepository sessionRepository;

    @Transactional
    public String signin(Login login) {

        userRepository.findByLoginId(login.getLoginId())
                .orElseThrow(UserNotFound::new);

        User user = userRepository.findByLoginIdAndPassword(login.getLoginId(), login.getPassword())
                .orElseThrow(InvalidSinginInformation::new);

        Session session = user.addSession();

        return session.getAccessToken();
    }
    @Transactional
    public void signup(StudentSignup signup) {
        Optional<User> userOptional = userRepository.findByLoginId(signup.getLoginId());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }
        Student student = Student.builder()
                .loginId(signup.getLoginId())
                .password(signup.getPassword())
                .name(signup.getName())
                .college(signup.getCollege())
                .phoneNum(signup.getPhoneNum())
                .studentUnm(signup.getStudentUnm())
                .build();

        userRepository.save(student);
    }
    @Transactional
    public void signup(CollegeSignup signup) {
        Optional<User> userOptional = userRepository.findByLoginId(signup.getLoginId());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }
        College college = College.builder()
                .loginId(signup.getLoginId())
                .password(signup.getPassword())
                .name(signup.getName())
                .instagram(signup.getInstagram())
                .build();

        userRepository.save(college);
    }
    @Transactional
    public void signup(PartnerSignup signup) {
        Optional<User> userOptional = userRepository.findByLoginId(signup.getLoginId());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }
        Partner partner = Partner.builder()
                .loginId(signup.getLoginId())
                .password(signup.getPassword())
                .name(signup.getName())
                .partnerNum(signup.getPartnerNum())
                .partnerType(signup.getPartnerType())
                .city(signup.getCity())
                .address1(signup.getAddress1())
                .address2(signup.getAddress2())
                .build();

        userRepository.save(partner);
    }

    public void logout(Session session) {
        User user = session.getUser();
        sessionRepository.delete(session);
        user.removeSession();
    }

    public Session getSession(NativeWebRequest webRequest) {
        HttpServletRequest serveletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        if(serveletRequest == null) {
            log.error("servlet request is null");
            throw new Unauthorized();
        }

        Cookie[] cookies = serveletRequest.getCookies();
        if(cookies.length==0){
            log.error("cookie is empty");
            throw new Unauthorized();
        }

        String accessToken = cookies[0].getValue();

        Session session = sessionRepository.findByAccessToken(accessToken)
                .orElseThrow(Unauthorized::new);

        return session;
    }

    public LoginIdResponse findLoginId(FindId findId) {
        User user = studentRepository.findByStudentUnm(findId.getStudentNum())
                .orElseThrow(UserNotFound::new);

        return LoginIdResponse.builder().loginid(user.getLoginId()).build();
    }

    public PwResponse findPw(FindPw findPW) {
        User user = studentRepository.findByLoginIdAndStudentUnm(findPW.getLoginId(), findPW.getStudentNum())
                .orElseThrow(UserNotFound::new);

        return PwResponse.builder().password(user.getPassword()).build();
    }

    public Student findByLoginId(String loginId) {
       return studentRepository.findByLoginId(loginId).orElseThrow(UserNotFound::new);
    }
}
