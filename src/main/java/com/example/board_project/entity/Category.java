package com.example.board_project.entity;

import lombok.Getter;
import lombok.Setter;

@Getter

public enum Category {
     QnA("Qna"),
    공지사항("공지사항"),
    모두보기("모두보기"),
    자유게시판("자유게시판");
     private String value;

     Category(String value) {
         this.value = value;
     }
}
