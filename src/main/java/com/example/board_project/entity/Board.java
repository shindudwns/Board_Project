package com.example.board_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Category category;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @Column
    private int hit;    //board 가 작성되는 순간 0 으로 설정

//    @Column(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY) // 보통은 manytoone 이 주인이 된다
    @JoinColumn(name = "userId") // 연관관계의 주인을 설정할 수 있음 (주인이 아닌 반대편은 읽기만 가능하고 외래키 변경을 하지 못하게하기 위함)
    private User user; // 1:n 관계에서 n을 user 에 써줌


    @JsonIgnoreProperties({"board"})//는 특정 필드가 직렬화는 허용하지만 역직렬화는 허용하지 않게 해주는 어노테이션입니다.
    @OneToMany(mappedBy = "board",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE) // // 1:n 관계에서 1을 replylist 에 써줌
    //fetch = FetchType.EAGER는 즉시 로딩 기능.
    //mappedBy도 마찬가지로 주인설정하는것
    private List<Reply> replyList;

    @CreationTimestamp
    private Timestamp createTime;
}
