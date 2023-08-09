package com.example.board_project.controller.api;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.UserJoinDto;
import com.example.board_project.dto.UserModifyDto;
import com.example.board_project.entity.User;
import com.example.board_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/auth/userJoin")
    public String userJoin(@ModelAttribute UserJoinDto userJoinDto) {
        userService.join(userJoinDto);
        //System.out.println(userJoinDto);
        return "redirect:/";
    }

    @PostMapping("/user/modify")
    public String modify(@ModelAttribute UserModifyDto userModifyDto) {
        userService.modify(userModifyDto);
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userModifyDto.getLoginId(), userModifyDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return "redirect:/";
    }

    @GetMapping("/user/delete/{userId}")
    public String delete(@PathVariable int userId) {
        userService.delete(userId);
        //회원 상세 정보 페이지에서 삭제
            return "redirect:/logout";
    }
    @GetMapping("/admin/user/delete/{userId}")
    public String deleteFromAdmin(@PathVariable int userId) {
        userService.delete(userId);
            return "redirect:/admin/user";
    }


    @PostMapping("/auth/saveIdCheck")
    @ResponseBody
    public String loginIdCheck(@RequestBody UserJoinDto userJoinDto) {
        User findUser = userService.saveIdCheck(userJoinDto.getLoginId());
        if (findUser != null) {
            return "중복";
        } else {
            return "사용가능";
        }

    }
    @PostMapping("/auth/modifyIdCheck")
    @ResponseBody
    public String loginIdCheck(@RequestBody UserJoinDto userJoinDto, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User findUser = userService.modifyIdCheck(userJoinDto.getLoginId(),principalDetail);
        if (findUser != null) {
            return "중복";
        } else {
            return "사용가능";
        }

    }
}