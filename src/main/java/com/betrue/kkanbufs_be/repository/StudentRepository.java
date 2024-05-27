package com.betrue.kkanbufs_be.repository;

import com.betrue.kkanbufs_be.domain.user.Student;
import com.betrue.kkanbufs_be.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<User> findByLoginIdAndPassword(String loginId,String password);
    Optional<User> findByLoginId(String loginId);
}
