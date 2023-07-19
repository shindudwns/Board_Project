package com.example.board_project.controller.api;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.ReplyDeleteDto;
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

import java.util.List;

@Controller
public class ReplyApiController {
    @Autowired
    private ReplyService replyService;

    @PostMapping("/reply/save")
    @ResponseBody
    public List<Reply> replySave(@RequestBody ReplySaveDto replySaveDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
        User user = principalDetail.getUser();
        Reply reply = replyService.join(replySaveDto, user);
        List<Reply> byBoardId = replyService.findByBoardId(replySaveDto.getBoardId());

        return byBoardId;
    }
    @PostMapping("/reply/modify")
    @ResponseBody
    public List<Reply> modify(@RequestBody ReplyModifyDto replyModifyDto) {
        System.out.println(replyModifyDto);
       replyService.modify(replyModifyDto);
        List<Reply> byBoardId = replyService.findByBoardId(replyModifyDto.getBoardId());
        return byBoardId;
    }
    @PostMapping("/reply/delete")
    @ResponseBody
    public List<Reply> delete(@RequestBody ReplyDeleteDto replyDeleteDto) {
        replyService.deleteById(replyDeleteDto.getReplyId());
        List<Reply> byBoardId = replyService.findByBoardId(replyDeleteDto.getBoardId());
        return byBoardId;
    }
}
