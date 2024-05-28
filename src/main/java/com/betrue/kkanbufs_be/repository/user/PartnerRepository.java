package com.betrue.kkanbufs_be.repository.user;

import com.betrue.kkanbufs_be.domain.user.Partner;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PartnerRepository extends CrudRepository<Partner, Long> {
    Optional<Partner> findByLoginId(String loginId);
}
