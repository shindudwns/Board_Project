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
        //유효성 체크 POST공격 방어
        if (!userModifyDto.getPhoneNumber().equals("000-0000-0000")) {
            User user = userRepository.findById(userModifyDto.getId()).get();
            user.setLoginId(userModifyDto.getLoginId());
            user.setPassword(encoder.encode(userModifyDto.getPassword()));
            user.setUsername(userModifyDto.getUsername());
            user.setName(userModifyDto.getName());
            user.setPhoneNumber(userModifyDto.getPhoneNumber());
            user.setCreateTime(userModifyDto.getCreateTime());
            userRepository.save(user);
        }
    }


    public void delete(int id) {
        userRepository.deleteById(id);
    }


    public User saveIdCheck(String newLoginId) {
        return userRepository.findByLoginId(newLoginId).orElse(null);
    }
    public User modifyIdCheck(String newLoginId,@AuthenticationPrincipal PrincipalDetail principalDetail) {
        String loginId = principalDetail.getUser().getLoginId();
        if(loginId.equals(newLoginId)){
            return null;
        }
        return userRepository.findByLoginId(newLoginId).orElse(null);
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
        return UserSelectDto.userToUserSelectDto(user);

    }

    public User findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId).orElse(null);
    }


}