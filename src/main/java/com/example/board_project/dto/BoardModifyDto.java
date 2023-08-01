package com.example.board_project.dto;

import com.example.board_project.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardModifyDto {
    private int id;
    private Category category;
    private String title;
    private String content;

}
