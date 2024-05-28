package com.betrue.kkanbufs_be.domain.user;

import com.betrue.kkanbufs_be.domain.Post;
import com.betrue.kkanbufs_be.domain.Session;
import com.betrue.kkanbufs_be.request.PostCreate;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String loginId; // 아이디

    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Session> sessions = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public Session addSession(){
        Session session = Session.builder()
                .user(this)
                .build();

        sessions.add(session);

        return session;
    }

    public void removeSession(Session session){
        sessions.remove(session);
    }

    public void addPost(Post post){
        posts.add(post);
    }

    public User(String name, String loginId, String password) {
        this.name = name;
        this.loginId = loginId;
        this.password = password;
    }
}
