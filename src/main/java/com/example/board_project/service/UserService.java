package com.example.board_project.service;

import com.example.board_project.dto.UserJoinDto;
import com.example.board_project.dto.UserModifyDto;
import com.example.board_project.entity.RoleType;
import com.example.board_project.entity.User;
import com.example.board_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;



    @Transactional
    public void join(UserJoinDto userJoinDto) {
        User user = User.builder()
                .loginId(userJoinDto.getLoginId())
                .password(encoder.encode(userJoinDto.getPassword()))
                .name(userJoinDto.getName())
                .phoneNumber(userJoinDto.getPhoneNumber())
                .username(userJoinDto.getUsername())
                .role(RoleType.USER)
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void modify(UserModifyDto userModifyDto) {

        User user = User.builder()
                .id(userModifyDto.getId())
                .loginId(userModifyDto.getLoginId())
                .password(encoder.encode(userModifyDto.getPassword()))
                .name(userModifyDto.getName())
                .phoneNumber(userModifyDto.getPhoneNumber())
                .username(userModifyDto.getUsername())
                .role(RoleType.USER)
                .createTime(userModifyDto.getCreateTime())
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User loginIdCheck(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElse(null);
        return user;
    }
}