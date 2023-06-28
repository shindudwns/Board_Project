package com.example.board_project.controller;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.entity.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(@RequestParam(value = "failLogin", required = false) boolean failLogin, Model model) {
        model.addAttribute("failLogin", failLogin);
        return "user/loginForm";
    }

    @GetMapping("/user/detail")
    public String detail(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = principalDetail.getUser();
        model.addAttribute("loginUser", user);
        return "/user/detail";
    }

    @GetMapping("/user/modifyForm")
    public String modifyForm(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = principalDetail.getUser();
        model.addAttribute("loginUser", user);
        return "/user/modifyForm";
    }


}