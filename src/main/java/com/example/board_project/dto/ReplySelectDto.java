package com.example.board_project.dto;

import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.User;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReplySelectDto {

    private int id;

    private String content;

    private UserDto userDto;

    private BoardDto boardDto; //이걸 왜 여기다 써줘야되는지

    private Reply parent;

    private List<Reply> child;

    private Timestamp createTime;



    static public ReplySelectDto replyToReplySelectDto(Reply reply) {
        return ReplySelectDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .userDto(UserDto.userToUserDto(reply.getUser()))
                .boardDto(BoardDto.boardToBoardDto(reply.getBoard()))
                .createTime(reply.getCreateTime())
                .parent(reply.getParent())
                .child(reply.getChild())
                .build();

    }
}
