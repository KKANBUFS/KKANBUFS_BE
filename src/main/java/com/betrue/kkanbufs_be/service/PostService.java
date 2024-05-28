package com.betrue.kkanbufs_be.service;

import com.betrue.kkanbufs_be.domain.Post;
import com.betrue.kkanbufs_be.domain.PostEditor;
import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.domain.user.User;
import com.betrue.kkanbufs_be.exception.PostNotFound;
import com.betrue.kkanbufs_be.exception.Unauthorized;
import com.betrue.kkanbufs_be.repository.PostRepository;
import com.betrue.kkanbufs_be.request.PostCreate;
import com.betrue.kkanbufs_be.request.PostEdit;
import com.betrue.kkanbufs_be.request.Search;
import com.betrue.kkanbufs_be.response.PostResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate, Session session){
        User user = session.getUser();
        //postCreate -> Entity
        Post post = Post.builder()
                .user(user)
                .title(postCreate.getTitle())
                .content(postCreate.getContent())
                .build();

        user.addPost(post);

       postRepository.save(post);
    }

    public PostResponse get(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .build();

    }
    public List<PostResponse> getList(Search postSearch) {
        //web -> page 1 -> 0 변환해줌 yml
        return postRepository.getList(postSearch).stream()
                .map(PostResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void edit(Long id, PostEdit postEdit, Session session) {
        Post post = postRepository.findById(id)
            .orElseThrow(PostNotFound::new);

        User user = session.getUser();

        if(!user.getId().equals(post.getUser().getId())) throw new Unauthorized();

        PostEditor.PostEditorBuilder editorBuilder = post.toEditor();

        PostEditor postEditor = editorBuilder.title(postEdit.getTitle())
                .content(postEdit.getContent())
                .build();

        post.edit(postEditor);

      /*  if(postEdit.getTitle() != null) {
            editorBuilder.title(postEdit.getTitle());
        }

        if(postEdit.getContent() != null) {
            editorBuilder.content(postEdit.getContent());
        }

        post.edit( editorBuilder.build());*/
    }

    public void delete(Long id, Session session) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFound::new);

        User user = session.getUser();

        if(!user.getId().equals(post.getUser().getId())) throw new Unauthorized();

        postRepository.delete(post);
    }
}
