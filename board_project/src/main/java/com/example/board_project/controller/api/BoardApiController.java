package com.example.board_project.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardApiController {
    @PostMapping("/board/save")
    public String boardSave() {

        return "redirect:/";
    }
}
