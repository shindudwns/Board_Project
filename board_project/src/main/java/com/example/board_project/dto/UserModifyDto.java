package com.example.board_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModifyDto {
    private int id;
    private String loginId;
    private String password;
    private String name;
    private String phoneNumber;
    private String username;
    private Timestamp createTime;
}