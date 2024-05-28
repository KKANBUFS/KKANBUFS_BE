package com.betrue.kkanbufs_be.repository;

import com.betrue.kkanbufs_be.domain.PartnerShip;
import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.domain.user.Partner;
import com.betrue.kkanbufs_be.domain.user.Student;
import com.betrue.kkanbufs_be.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PartnerShipRepository extends CrudRepository<PartnerShip, Long> ,PartnerShipRepositoryCustom {
    Optional<PartnerShip> findByPartnerAndCollege(Partner partner, College college);
    Optional<PartnerShip> findByCollege(College college);
}
