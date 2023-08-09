package com.example.board_project.dto;

import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.User;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    private BoardDto boardDto;

    private boolean rootReply;

    private ReplyDto parent;

    private List<ReplyDto> child;

    private Timestamp createTime;


    public static ReplySelectDto replyToReplySelectDto(Reply reply) {
        List<Reply> replyList = reply.getChild();
        List<ReplyDto> replyDtoList = new ArrayList<>();
        for (Reply reply1 : replyList) {
            replyDtoList.add(ReplyDto.replyToReplyDto(reply1));
        }

        if (reply.isRootReply()) {
            return ReplySelectDto.builder()
                    .id(reply.getId())
                    .content(reply.getContent())
                    .userDto(UserDto.userToUserDto(reply.getUser()))
                    .boardDto(BoardDto.boardToBoardDto(reply.getBoard()))
                    .rootReply(reply.isRootReply())
                    .parent(null)
                    .createTime(reply.getCreateTime())
                    .child(replyDtoList)
                    .build();
        } else {
            return ReplySelectDto.builder()
                    .id(reply.getId())
                    .content(reply.getContent())
                    .userDto(UserDto.userToUserDto(reply.getUser()))
                    .boardDto(BoardDto.boardToBoardDto(reply.getBoard()))
                    .createTime(reply.getCreateTime())
                    .rootReply(reply.isRootReply())
                    .parent(ReplyDto.replyToReplyDto(reply.getParent()))
                    .child(replyDtoList)
                    .build();
        }
    }
}
