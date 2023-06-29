package com.example.board_project.controller.api;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.BoardSaveDto;
import com.example.board_project.entity.User;
import com.example.board_project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardApiController {

    @Autowired
    private BoardService boardService;

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
}
