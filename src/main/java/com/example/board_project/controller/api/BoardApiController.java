package com.example.board_project.controller.api;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.*;
import com.example.board_project.entity.Board;
import com.example.board_project.entity.User;
import com.example.board_project.service.BoardService;
import com.example.board_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardApiController {


    private final BoardService boardService;
    private final UserService userService;

    public BoardApiController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @PostMapping("/board/save")
    public String boardSave(BoardSaveDto boardSaveDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = principalDetail.getUser();
        boardService.join(boardSaveDto, user);
        return "redirect:/";
    }

    @GetMapping("/board/delete/{boardId}")
    public String delete(@PathVariable int boardId) {
        boardService.deleteById(boardId);
        return "redirect:/";
    }
    @GetMapping("/admin/board/delete/{boardId}")
    public String DeleteFromAdmin(@PathVariable int boardId) {
        boardService.deleteById(boardId);
        return "redirect:/admin/board";
    }

    @PostMapping("/board/modify")
    public String modify(@ModelAttribute BoardModifyDto boardModifyDto, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        UserSelectDto userSelectDto = userService.takeLoginUser(principalDetail);
        BoardSelectDto boardSelectDto = boardService.modify(boardModifyDto);
        model.addAttribute("board", boardSelectDto);
        model.addAttribute("loginUser", userSelectDto);
        return "redirect:/board/detail/" + boardSelectDto.getId();
    }


}
