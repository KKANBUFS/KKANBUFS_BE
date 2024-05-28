package com.betrue.kkanbufs_be.repository.user;

import com.betrue.kkanbufs_be.domain.user.Student;
import com.betrue.kkanbufs_be.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StudentRepository extends CrudRepository<Student, Integer> {
    Optional<Student> findByLoginId(String loginId);
    Optional<Student> findByStudentUnm(String studentNum);
    Optional<Student> findByLoginIdAndStudentUnm(String loginId,String studentNum);
}
