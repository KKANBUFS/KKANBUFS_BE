/*
package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.domain.Post;
import com.betrue.kkanbufs_be.repository.PostRepository;
import com.betrue.kkanbufs_be.request.PostCreate;
import com.betrue.kkanbufs_be.request.PostEdit;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach//각메서드 실행 전에 실행
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성 요청시 ")
    void test() throws Exception {
        //when
        //PostCreate request = new PostCreate("제목입니다.","내용입니다.");
        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);//getter 로 제이슨으로 가공해줌

        System.out.println(json);

        //expected
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());
    }

    @Test
    @DisplayName("/posts 요청시 title값이 필수다.")
    void test2() throws Exception {

        PostCreate request = PostCreate.builder()
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다."))
                .andExpect(jsonPath("$.validation.title").value("타이틀을 입력해주세요"))
                .andDo(print());

    }

    @Test
    @DisplayName("/posts 요청시 DB값 저장")
    void Saved_DB_POST_Request() throws Exception {

        PostCreate request = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);//getter 로 제이슨으로 가공해줌

        //when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());
        //then
        //assertThat(postRepository.count()).isEqualTo(1);
        assertEquals(1L, postRepository.count());

        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    public void test4() throws Exception{
        //given
        Post post = Post.builder()
                .title("123456789012345")
                .content("bar")
                .build();
        postRepository.save(post);

        //expected
        mockMvc.perform(get("/posts/{postId}", post.getId())
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("1234567890"))
                .andExpect(jsonPath("$.content").value("bar"))
                .andDo(print());
    }

    @Test
    @DisplayName("글 1페이지 조회")
    public void test5() throws Exception{
        //given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("daile title " + i)
                        .content("daile content " + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        //expected
        mockMvc.perform(get("/posts?page=1&size=10")
                        .contentType(APPLICATION_JSON))
                 .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(10)))
                .andExpect(jsonPath("$[0].title").value("daile title 30"))
                .andExpect(jsonPath("$[0].content").value("daile content 30"))
                .andDo(print());
        */
/*기존
         * {id: ..., title:...}
         *//*

        */
/*List 조회 시
         * [{id: ..., title:...},{id: ..., title:...}]
         *//*

    }
    @Test
    @DisplayName("글 페이지 0으로 해도 1페이지 조회")
    public void test6() throws Exception{
        //given
        List<Post> requestPosts = IntStream.range(1, 31)
                .mapToObj(i -> Post.builder()
                        .title("daile title " + i)
                        .content("daile content " + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        //expected
        mockMvc.perform(get("/posts?page=0&size=10")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(10)))
                .andExpect(jsonPath("$[0].title").value("daile title 30"))
                .andExpect(jsonPath("$[0].content").value("daile content 30"))
                .andDo(print());
        */
/*기존
         * {id: ..., title:...}
         *//*

        */
/*List 조회 시
         * [{id: ..., title:...},{id: ..., title:...}]
         *//*

    }

    @Test
    @DisplayName("글 제목 수정")
    public void test7() throws Exception{
        //given
        Post post = Post.builder()
                .title("daile title")
                .content("daile content")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("변경된 제목")
                .content("daile content")
                .build();

        //expected
        mockMvc.perform(patch("/posts/{postId}", post.getId()) //PATCH /posts/{postI d}
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("글 내용 수정")
    public void test8() throws Exception{
        //given
        Post post = Post.builder()
                .title("daile title")
                .content("daile content")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("daile title")
                .content("변경된 내용")
                .build();

        //expected
        mockMvc.perform(patch("/posts/{postId}", post.getId()) //PATCH /posts/{postI d}
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    @DisplayName("존재하지 않는 게시글 조회")
    public void test9() throws Exception{
        //expected
        mockMvc.perform(delete("/posts/{postId}",1L) //PATCH /posts/{postI d}
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @DisplayName("존재하지 않는 게시글 조회")
    public void test10() throws Exception{
        PostEdit postEdit = PostEdit.builder()
                .title("daile title")
                .content("daile content")
                .build();

        //expected
        mockMvc.perform(patch("/posts/{postId}",1L) //PATCH /posts/{postI d}
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postEdit)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    @DisplayName("게시글 작성시 제목에 '바보'는 포함 될 수 없다")
    void test11() throws Exception {

        PostCreate request = PostCreate.builder()
                .title("나는 바보입니다.")
                .content("내용입니다.")
                .build();

        String json = objectMapper.writeValueAsString(request);//getter 로 제이슨으로 가공해줌

        //when
        mockMvc.perform(post("/posts")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
}
*/
