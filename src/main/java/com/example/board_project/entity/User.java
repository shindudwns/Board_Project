package com.example.board_project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String loginId;    //로그인할때 id

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String username;    //별명

    @Enumerated(EnumType.STRING)    //해당 Enum이 스트링이 라고 알려준다
    private RoleType role;    //Enum을 쓰는게 좋다

    @JsonIgnoreProperties({"user"})//는 특정 필드가 직렬화는 허용하지만 역직렬화는 허용하지 않게 해주는 어노테이션입니다.
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // // 1:n 관계에서 1을 replylist 에 써줌
    //fetch = FetchType.EAGER는 즉시 로딩 기능.
    //mappedBy도 마찬가지로 주인설정하는것
    private List<Reply> replyList;

    @JsonIgnoreProperties({"user"})//는 특정 필드가 직렬화는 허용하지만 역직렬화는 허용하지 않게 해주는 어노테이션입니다(무한참조 방지).
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE) // // 1:n 관계에서 1을 replylist 에 써줌
    //fetch = FetchType.EAGER는 즉시 로딩 기능.
    //mappedBy도 마찬가지로 주인설정하는것
    private List<Board> boardList;

    @CreationTimestamp
    private Timestamp createTime;
}
