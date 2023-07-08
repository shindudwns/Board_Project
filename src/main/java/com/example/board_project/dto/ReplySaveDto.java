package com.example.board_project.dto;


import com.example.board_project.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class ReplySaveDto {
    private int boardId;
    private String content;
}
