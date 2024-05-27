package com.betrue.kkanbufs_be.service;

import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.domain.User;
import com.betrue.kkanbufs_be.exception.AlreadyExistsEmailException;
import com.betrue.kkanbufs_be.exception.InvalidSinginInformation;
import com.betrue.kkanbufs_be.exception.UserNotFound;
import com.betrue.kkanbufs_be.repository.UserRepository;
import com.betrue.kkanbufs_be.request.Login;
import com.betrue.kkanbufs_be.request.Signup;
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
    public void signup(Signup signup) {
        Optional<User> userOptional = userRepository.findByLoginId(signup.getLoginId());
        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }
        userRepository.save(User.builder()
                .name(signup.getName())
                .password(signup.getPassword())
                .loginId(signup.getLoginId())
                .build());
    }
}
