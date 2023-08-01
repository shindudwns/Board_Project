package com.example.board_project.dto;

import com.example.board_project.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardSaveDto {
    private String title;
    private String content;
    private Category category;
}
