package com.example.board_project.dto;

import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
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
public class BoardSelectDto {

    private int id;

    private String title;

    private String content;

    private int hit;    //board 가 작성되는 순간 0 으로 설정

    private UserDto userDto;

    private List<ReplyDto> replyDtoList;

    private Timestamp createTime;

    static public BoardSelectDto boardToBoardSelectDto(Board board) {
        List<Reply> replyList = board.getReplyList();
        List<ReplyDto> replyDtoList1 = new ArrayList<>();

        for (Reply reply : replyList) {
            replyDtoList1.add(ReplyDto.replyToReplyDto(reply));
        }
        BoardSelectDto boardDto = BoardSelectDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .hit(board.getHit())
                .userDto(UserDto.userToUserDto(board.getUser()))
                .replyDtoList(replyDtoList1)
                .createTime(board.getCreateTime())
                .build();

        return boardDto;

    }
}
