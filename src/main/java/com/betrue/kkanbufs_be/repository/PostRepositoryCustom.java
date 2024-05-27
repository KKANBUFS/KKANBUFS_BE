package com.betrue.kkanbufs_be.repository;

import com.betrue.kkanbufs_be.domain.Post;
import com.betrue.kkanbufs_be.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
