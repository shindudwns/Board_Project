package com.example.board_project.controller.api;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.ReplyModifyDto;
import com.example.board_project.dto.ReplySaveDto;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.User;
import com.example.board_project.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReplyApiController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/save")
    @ResponseBody
    public String replySave(@RequestBody ReplySaveDto replySaveDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
        System.out.println("-----------------------------------------------------"+replySaveDto);

        User user = principalDetail.getUser();
        replyService.join(replySaveDto,user);
        return "true";
    }
    @PostMapping("/reply/modify")
    public String modify(@ModelAttribute ReplyModifyDto replyModifyDto, Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User loginUser = principalDetail.getUser();
       // System.out.println("================================="+replyModifyDto);
        Reply reply = replyService.modify(replyModifyDto);
        model.addAttribute("reply", reply);
        model.addAttribute("loginUser", loginUser);
        return "/board/detail";
    }

}
