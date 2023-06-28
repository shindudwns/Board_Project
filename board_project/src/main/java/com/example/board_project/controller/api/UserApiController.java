package com.example.board_project.controller.api;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.UserJoinDto;
import com.example.board_project.dto.UserModifyDto;
import com.example.board_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/user/delete")
    public String delete(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        int id = principalDetail.getUser().getId();
        userService.delete(id);
        return "redirect:/logout";
    }
}
