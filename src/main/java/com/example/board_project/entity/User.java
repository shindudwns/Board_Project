package com.example.board_project.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true,nullable = false)
    private  String loginId;    //로그인할때 id

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true,nullable = false)
    private String phoneNumber;

    @Column(unique = true,nullable = false)
    private String username;    //별명

    @Enumerated(EnumType.STRING)    //해당 Enum이 스트링이 라고 알려준다
    private RoleType role;    //Enum을 쓰는게 좋다

    @CreationTimestamp
    private Timestamp createTime;
}
