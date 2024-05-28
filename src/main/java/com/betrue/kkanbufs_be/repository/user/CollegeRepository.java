package com.betrue.kkanbufs_be.repository.user;

import com.betrue.kkanbufs_be.domain.user.College;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CollegeRepository extends CrudRepository<College, Long> {
    Optional<College> findByLoginId(String loginId);
}
