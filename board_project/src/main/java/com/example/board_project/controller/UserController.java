package com.example.board_project.controller;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/auth/test")
    public String test() {
        return "test";
    }

    @GetMapping("/user/detail")
    public String detail(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = principalDetail.getUser();
        System.out.println("-------------------------------"+user);
        model.addAttribute("loginUser", user);
        return "/user/detail";
    }
}
