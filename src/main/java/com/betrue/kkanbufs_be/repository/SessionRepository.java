package com.betrue.kkanbufs_be.repository;

import com.betrue.kkanbufs_be.domain.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session, Integer> {
    Optional<Session> findByAccessToken(String accessToken);
}
