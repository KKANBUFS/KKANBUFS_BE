package com.betrue.kkanbufs_be.repository;


import com.betrue.kkanbufs_be.domain.PartnerShip;
import com.betrue.kkanbufs_be.domain.user.College;
import com.betrue.kkanbufs_be.request.Search;

import java.util.List;

public interface PartnerShipRepositoryCustom {
    List<PartnerShip> getList(Search postSearch);
    List<PartnerShip> getListByCollege(College college);
}
