package com.example.board_project.service;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.UserDto;
import com.example.board_project.dto.UserJoinDto;
import com.example.board_project.dto.UserModifyDto;
import com.example.board_project.dto.UserSelectDto;
import com.example.board_project.entity.RoleType;
import com.example.board_project.entity.User;
import com.example.board_project.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;




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


    public void modify(UserModifyDto userModifyDto) {
        System.out.println(userModifyDto);
        User user = userRepository.findById(userModifyDto.getId()).get();
        user.setLoginId(userModifyDto.getLoginId());
        user.setPassword(encoder.encode(userModifyDto.getPassword()));
        user.setUsername(userModifyDto.getUsername());
        user.setName(userModifyDto.getName());
        user.setPhoneNumber(userModifyDto.getPhoneNumber());
        user.setCreateTime(userModifyDto.getCreateTime());
        userRepository.save(user);
    }


    public void delete(int id) {
        userRepository.deleteById(id);
    }


    public User loginIdCheck(String loginId) {
        User user = userRepository.findByLoginId(loginId).orElse(null);
        return user;
    }

    public List<UserSelectDto>  findAll() {
        List<User> userList = userRepository.findAll();
        List<UserSelectDto> userSelectDtoList = new ArrayList<>();
        for (User user : userList) {
            userSelectDtoList.add(UserSelectDto.userToUserSelectDto(user));
        }
        return userSelectDtoList;
    }


    public UserSelectDto takeLoginUser(@AuthenticationPrincipal PrincipalDetail principalDetail) {
        User user = userRepository.findById(principalDetail.getUser().getId()).get();
        UserSelectDto userSelectDto = UserSelectDto.userToUserSelectDto(user);
        return userSelectDto;

    }
}