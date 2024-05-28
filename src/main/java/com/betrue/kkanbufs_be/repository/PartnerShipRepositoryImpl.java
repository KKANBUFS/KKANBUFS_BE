package com.betrue.kkanbufs_be.repository;

import com.betrue.kkanbufs_be.domain.PartnerShip;
import com.betrue.kkanbufs_be.domain.QPartnerShip;
import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.request.Search;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.betrue.kkanbufs_be.domain.QPartnerShip.partnerShip;

@RequiredArgsConstructor
public class PartnerShipRepositoryImpl implements PartnerShipRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<PartnerShip> getList(Search search) {
        return jpaQueryFactory.selectFrom(partnerShip)
                .limit(search.getSize())
                .offset(search.getOffset())
                .orderBy(partnerShip.Id.desc())
                .fetch();
    }

    @Override
    public List<PartnerShip> getListByCollege(College college) {
        return jpaQueryFactory.selectFrom(partnerShip)
                .fetch();
    }
}
