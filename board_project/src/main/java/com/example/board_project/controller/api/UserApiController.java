package com.example.board_project.controller.api;

import com.example.board_project.dto.UserJoinDto;
import com.example.board_project.model.User;
import com.example.board_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/userJoin")
    public String userJoin(@ModelAttribute UserJoinDto userJoinDto) {
        userService.join(userJoinDto);
        //System.out.println(userJoinDto);
        return "redirect:/";
    }
}
