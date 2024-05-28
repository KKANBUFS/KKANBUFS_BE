package com.betrue.kkanbufs_be.repository;

import com.betrue.kkanbufs_be.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLoginIdAndPassword(String loginId,String password);
    Optional<User> findByLoginId(String loginId);
    Optional<User> findByStudentNum(String studentNum);
    Optional<User> findByLoginIdAndStudentNum(String loginId,String studentNum);
}
