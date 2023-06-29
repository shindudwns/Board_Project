package com.example.board_project.controller;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.entity.Board;
import com.example.board_project.entity.User;
import com.example.board_project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;
    @GetMapping("/board/write")
    public String write() {
        return "board/writeForm";
    }

    @GetMapping("/board/detail/{boardId}")
    public String detailForm(@PathVariable int boardId, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User loginUser = principalDetail.getUser();
        Board board = boardService.findById(boardId);
        model.addAttribute("board", board);
        model.addAttribute("loginUser", loginUser);
        return "/board/detail";
    }

    @GetMapping("/board/modifyForm/{boardId}")
    public String modifyForm(@PathVariable int boardId,Model model) {
        Board board = boardService.findById(boardId);
        model.addAttribute("board", board);
        return "/board/modifyForm/"+boardId;
    }
}
