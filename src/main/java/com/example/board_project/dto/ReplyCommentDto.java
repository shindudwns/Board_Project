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
//대댓글 기능을 사용하기위한 Dto
public class ReplyCommentDto {

    private int id;

    private String content;

    private UserDto userDto;

    private BoardDto boardDto;

    private boolean rootReply;

    private ReplyDto parent;

    private List<ReplyCommentDto> child;

    private Timestamp createTime;


    static public ReplyCommentDto replyToReplyCommentDto(Reply reply) {
        System.out.println(reply.getChild());
        List<Reply> replyList = reply.getChild();
        List<ReplyCommentDto> replyCommentDtoList = new ArrayList<>();
        for (Reply reply1 : replyList) {
            replyCommentDtoList.add(ReplyCommentDto.replyToReplyCommentDto(reply1));
        }
        if (reply.isRootReply()) {
            return ReplyCommentDto.builder()
                    .id(reply.getId())
                    .content(reply.getContent())
                    .userDto(UserDto.userToUserDto(reply.getUser()))
                    .boardDto(BoardDto.boardToBoardDto(reply.getBoard()))
                    .rootReply(reply.isRootReply())
                    .parent(null)
                    .createTime(reply.getCreateTime())
                    .child(replyCommentDtoList)
                    .build();
        } else {
            return ReplyCommentDto.builder()
                    .id(reply.getId())
                    .content(reply.getContent())
                    .userDto(UserDto.userToUserDto(reply.getUser()))
                    .boardDto(BoardDto.boardToBoardDto(reply.getBoard()))
                    .createTime(reply.getCreateTime())
                    .rootReply(reply.isRootReply())
                    .parent(ReplyDto.replyToReplyDto(reply.getParent()))
                    .child(replyCommentDtoList)
                    .build();
        }
    }
}
