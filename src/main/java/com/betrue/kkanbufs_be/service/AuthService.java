package com.betrue.kkanbufs_be.service;

import com.betrue.kkanbufs_be.domain.*;
import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.Partner;
import com.betrue.kkanbufs_be.domain.user.Student;
import com.betrue.kkanbufs_be.domain.user.User;
import com.betrue.kkanbufs_be.exception.AlreadyExistsEmailException;
import com.betrue.kkanbufs_be.exception.InvalidSinginInformation;
import com.betrue.kkanbufs_be.exception.UserNotFound;
import com.betrue.kkanbufs_be.repository.UserRepository;
import com.betrue.kkanbufs_be.request.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;

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
                .dept(signup.getDept())
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
}
