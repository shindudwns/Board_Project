package com.example.board_project.controller;

import com.example.board_project.entity.Board;
import com.example.board_project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String index(Model model) {
        List<Board> boardList= boardService.takeAll();
        model.addAttribute("boardList", boardList);
        return "index";
    }
}
