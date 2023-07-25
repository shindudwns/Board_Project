package com.example.board_project.dto;

import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.RoleType;
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
public class UserSelectDto {  //오직 User의 정보만 갖고있다.
    private int id;

    private  String loginId;    //로그인할때 id

    private String password;

    private String name;

    private String phoneNumber;

    private String username;    //별명

    private RoleType role;    //Enum을 쓰는게 좋다

    private List<BoardDto> boardDtoList;

    private List<ReplyDto> replyDtoList;

    private Timestamp createTime;

    static public UserSelectDto userToUserSelectDto(User user){
        List<Reply> replyList = user.getReplyList();
        List<ReplyDto> replyDtoList1 = new ArrayList<>();

        for (Reply reply : replyList) {
            replyDtoList1.add(ReplyDto.replyToReplyDto(reply));
        }
        List<Board> boardList = user.getBoardList();
        List<BoardDto> boardDtoList1 = new ArrayList<>();

        for (Board board : boardList) {
            boardDtoList1.add(BoardDto.boardToBoardDto(board));
        }


        UserSelectDto proxyUser = UserSelectDto.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .username(user.getUsername())
                .boardDtoList(boardDtoList1)
                .replyDtoList(replyDtoList1)
                .createTime(user.getCreateTime())
                .build();
        return  proxyUser;
    }
}
