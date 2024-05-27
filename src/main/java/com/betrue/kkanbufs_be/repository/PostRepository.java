package com.betrue.kkanbufs_be.repository;

import com.betrue.kkanbufs_be.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> ,PostRepositoryCustom {
}
