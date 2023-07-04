package com.example.board_project.controller.api;


import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.BoardModifyDto;
import com.example.board_project.dto.ReplyModifyDto;
import com.example.board_project.dto.ReplySaveDto;
import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.User;
import com.example.board_project.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/save")
    public String replySave(ReplySaveDto replySaveDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
        User user = principalDetail.getUser();
        replyService.join(replySaveDto,user);
        return "redirect:/";
    }
    @GetMapping("/board/delete/{replyId}")
    public String delete(@PathVariable int replyId) {
        replyService.deleteById(replyId);
        return "redirect:/";
    }

    @PostMapping("/reply/modify")
    public String modify(@ModelAttribute ReplyModifyDto replyModifyDto, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User loginUser = principalDetail.getUser();
        System.out.println("================================="+replyModifyDto);
        Reply reply = replyService.modify(replyModifyDto);
        model.addAttribute("reply", reply);
        model.addAttribute("loginUser", loginUser);
        return "/board/detail";
    }

}
