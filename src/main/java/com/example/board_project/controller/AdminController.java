package com.example.board_project.controller;

import com.example.board_project.dto.BoardSelectDto;
import com.example.board_project.dto.ReplySelectDto;
import com.example.board_project.dto.UserSelectDto;
import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.User;
import com.example.board_project.service.BoardService;
import com.example.board_project.service.ReplyService;
import com.example.board_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final BoardService boardService;
    private final ReplyService replyService;

    public AdminController(UserService userService, BoardService boardService, ReplyService replyService) {
        this.userService = userService;
        this.boardService = boardService;
        this.replyService = replyService;
    }


    @GetMapping("/admin/user")
    public String userManage(Model model) {
        List<UserSelectDto> userSelectDtoList = userService.findAll();
        model.addAttribute("userList", userSelectDtoList);
        return "admin/user";
    }
    @GetMapping("/admin/board")
    public String boardManage(Model model) {
        List<BoardSelectDto> boardSelectDtoList = boardService.findAll();
        model.addAttribute("boardList", boardSelectDtoList);
        return "admin/board";
    }
    @GetMapping("/admin/reply")
    public String replyManage(Model model) {
        List<ReplySelectDto> replySelectDtoList = replyService.findAll();
        model.addAttribute("replyList", replySelectDtoList);
        return "admin/reply";
    }
}
