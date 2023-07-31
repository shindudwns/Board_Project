package com.example.board_project.dto;

import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.RoleType;
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
public class UserDto {  //오직 User의 정보만 갖고있다.
    private int id;

    private  String loginId;    //로그인할때 id

    private String password;

    private String name;

    private String phoneNumber;

    private String username;    //별명

    private RoleType role;    //Enum을 쓰는게 좋다

    private Timestamp createTime;

    static public UserDto userToUserDto(User user){
        UserDto proxyUser = UserDto.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .password(user.getPassword())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .username(user.getUsername())
                .role(user.getRole())
                .createTime(user.getCreateTime())
                .build();
        return  proxyUser;
    }
}
