package com.betrue.kkanbufs_be.repository.user;

import com.betrue.kkanbufs_be.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLoginIdAndPassword(String loginId,String password);
    Optional<User> findByLoginId(String loginId);
}
