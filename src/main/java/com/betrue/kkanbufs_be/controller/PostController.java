package com.betrue.kkanbufs_be.controller;

import com.betrue.kkanbufs_be.config.data.UserSession;
import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.exception.Unauthorized;
import com.betrue.kkanbufs_be.exception.UserNotFound;
import com.betrue.kkanbufs_be.repository.PostRepository;
import com.betrue.kkanbufs_be.request.PostCreate;
import com.betrue.kkanbufs_be.request.PostEdit;
import com.betrue.kkanbufs_be.request.PostSearch;
import com.betrue.kkanbufs_be.response.PostResponse;
import com.betrue.kkanbufs_be.service.AuthService;
import com.betrue.kkanbufs_be.service.PostService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.net.http.HttpHeaders;
import java.util.List;

//SSR -> jsp,thymeleaf,mustache, freemarker
// -> html rendering
//SPA -> vue
// -> javascript + <-> API (JSON)
// vue, nuxt
// react, next

// Case1. 저장한 데이터 Entity -> response로 응답하기
//        //Case2. 저장한 데이터의 primary_id -> response로 응답하기
//        //      client에서는 수신한 id를 글 조회 API를 통해서 데이터를 수신받음
//        //Case3. 응답 필요없음 -> 클라이언트에서 모든 POST(글) 데이터 context를 잘 관리함
//        // Bad Case: 서버에서 -> 반드시 이렇게 할껍니다. !fix
//        //      -> 서버에서 차라리 유연하게 대응하는게 좋습니다. -> 코드를 잘 짜야함
//        //      -> 한번에  일괄적으로 잘 처리되는 케이스가 없습니다. -> 잘관리하는 형태가 중요함

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final AuthService authService;

    //글 등록
    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request,NativeWebRequest webRequest){
        request.validate();
        Session session = authService.getSession(webRequest);
        if (session == null) {throw new Unauthorized();}
        postService.write(request,session);
    }

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name = "postId") Long id) {
        return postService.get(id);
    }

    @GetMapping("/posts")
    public List<PostResponse> getAll(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }

    @PatchMapping("/posts/{postId}")
    public void edit(@PathVariable(name = "postId") Long postId, @RequestBody @Valid PostEdit request,NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null) {throw new Unauthorized();}
        postService.edit(postId, request,session);
    }

    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable(name = "postId") Long id, NativeWebRequest webRequest) {
        Session session = authService.getSession(webRequest);
        if (session == null) {throw new Unauthorized();}
        postService.delete(id,session);
    }
}

