package com.example.board_project.controller;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.BoardSelectDto;
import com.example.board_project.entity.Board;
import com.example.board_project.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.example.board_project.entity.RoleType.ADMIN;
import static com.example.board_project.entity.RoleType.USER;

@Controller
public class IndexController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/")
    public String index(Model model,    //페이지 설정을 위한 @PageableDefault 설정
                        @PageableDefault(size = 8, page = 0, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @AuthenticationPrincipal PrincipalDetail principalDetail) {
        if (principalDetail!=null&&principalDetail.getUser().getRole()==ADMIN) {
            return "/admin/index";

        }
            Page<BoardSelectDto> boardSelectDtoPage = boardService.takeAll(pageable);
            int nowPage = boardSelectDtoPage.getPageable().getPageNumber() + 1;
            int startPage = (nowPage - 1) / 5 * 5 + 1;
            int endPage = (startPage + 4 > boardSelectDtoPage.getTotalPages()) ? boardSelectDtoPage.getTotalPages() : startPage + 4;

            model.addAttribute("boardList", boardSelectDtoPage);
            model.addAttribute("startPage", startPage);
            model.addAttribute("nowPage", nowPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("searchTitle", null);
            return "index";
    }
}
