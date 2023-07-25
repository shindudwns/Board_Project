package com.example.board_project.dto;

import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.User;
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
@Builder
@ToString
public class BoardDto { //오직 Board의 정보만 갖고있다.

    private int id;

    private String title;

    private String content;

    private int hit;    //board 가 작성되는 순간 0 으로 설정

    private Timestamp createTime;

    static public BoardDto boardToBoardDto(Board board) {
        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .hit(board.getHit())

                .createTime(board.getCreateTime())
                .build();
        return boardDto;

    }
}
