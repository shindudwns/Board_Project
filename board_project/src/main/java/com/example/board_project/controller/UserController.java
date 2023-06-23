package com.example.board_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/userJoin")
    public String joinForm() {
        return "user/joinForm";
    }
    @GetMapping("/auth/loginForm")
    public String loginForm

            () {
        return "user/joinForm";
    }
}
