package com.example.board_project.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private User user;
    @Column
    private int hits;
//    private Board board;  board를 추가하는 것이 맞을까 board한테 reply 목록이 있기때문에 고민
    @CreationTimestamp
    private Timestamp createTime;
}
