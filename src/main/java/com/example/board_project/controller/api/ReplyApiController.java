package com.example.board_project.controller.api;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.*;
import com.example.board_project.service.ReplyService;
import com.example.board_project.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public List<ReplyCommentDto> replySave(@RequestBody ReplySaveDto replySaveDto, @AuthenticationPrincipal PrincipalDetail principalDetail){
        replyService.join(replySaveDto,principalDetail);
        return replyService.findByBoardId(replySaveDto.getBoardId());

    }
    @PostMapping("/reply/modify")
    @ResponseBody
    public List<ReplyCommentDto> modify(@RequestBody ReplyModifyDto replyModifyDto) {
       replyService.modify(replyModifyDto);
        return replyService.findByBoardId(replyModifyDto.getBoardId());
    }
    @PostMapping("/reply/delete")
    @ResponseBody
    public List<ReplyCommentDto> delete(@RequestBody ReplyDeleteDto replyDeleteDto) {
        replyService.deleteById(replyDeleteDto.getReplyId());
        return replyService.findByBoardId(replyDeleteDto.getBoardId());
    }
    @GetMapping("admin/reply/delete/{replyId}")
    public String deleteFromAdmin(@PathVariable int replyId) {
        replyService.deleteById(replyId);
        return "redirect:/admin/reply";
    }
    @PostMapping("/reply/commentSave")
    @ResponseBody
    public List<ReplyCommentDto> commentSave(@RequestBody ReplyDto replyDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        return replyService.commentReply(replyDto, principalDetail);
    }
}
