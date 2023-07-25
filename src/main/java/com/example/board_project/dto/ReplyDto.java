package com.example.board_project.dto;

import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReplyDto { //오직 Reply의 정보만 갖고있다.

    private int id;

    private String content;

    private Timestamp createTime;

    static public ReplyDto replyToReplyDto(Reply reply) {
        ReplyDto replySelectDto = ReplyDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .createTime(reply.getCreateTime())
                .build();
        return replySelectDto;

    }
}
