package com.example.board_project.service;

import com.example.board_project.dto.UserJoinDto;
import com.example.board_project.model.RoleType;
import com.example.board_project.model.User;
import com.example.board_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void join(UserJoinDto userJoinDto) {
        User user = User.builder()
                .loginId(userJoinDto.getLoginId())
                .password(userJoinDto.getPassword())
                .name(userJoinDto.getName())
                .phoneNumber(userJoinDto.getPhoneNumber())
                .username(userJoinDto.getUsername())
                .role(RoleType.USER)
                .build();
        userRepository.save(user);
    }
}
