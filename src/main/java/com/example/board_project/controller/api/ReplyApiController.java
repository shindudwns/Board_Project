package com.example.board_project.controller.api;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.*;
import com.example.board_project.service.ReplyService;
import com.example.board_project.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReplyApiController {
    private final UserService userService;

    private final ReplyService replyService;

    public ReplyApiController(UserService userService, ReplyService replyService) {

        this.userService = userService;
        this.replyService = replyService;
    }

    @PostMapping("/reply/save")
    @ResponseBody
    public List<ReplySelectDto> replySave(@RequestBody ReplySaveDto replySaveDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
        replyService.join(replySaveDto,principalDetail);
        List<ReplySelectDto> replySelectDtoList = replyService.findByBoardId(replySaveDto.getBoardId());
        return replySelectDtoList;
    }
    @PostMapping("/reply/modify")
    @ResponseBody
    public List<ReplySelectDto> modify(@RequestBody ReplyModifyDto replyModifyDto) {
        System.out.println(replyModifyDto);
       replyService.modify(replyModifyDto);
        List<ReplySelectDto> replySelectDtoList = replyService.findByBoardId(replyModifyDto.getBoardId());
        return replySelectDtoList;
    }
    @PostMapping("/reply/delete")
    @ResponseBody
    public List<ReplySelectDto> delete(@RequestBody ReplyDeleteDto replyDeleteDto) {
        replyService.deleteById(replyDeleteDto.getReplyId());
        List<ReplySelectDto> replySelectDtoList = replyService.findByBoardId(replyDeleteDto.getBoardId());
        return replySelectDtoList;
    }
}
