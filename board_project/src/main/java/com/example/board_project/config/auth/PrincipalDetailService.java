package com.example.board_project.config.auth;

import com.example.board_project.model.User;
import com.example.board_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //스프링이 로그인 요청을 가로챌 때 , username,    password   변수 2개를 가로채는데
    //password 부분 처리는 알아서 함
    //username이 DB에 있는지만 확인해주면 된다.

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(loginId).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 아이디를 찾을수 없습니다!");
        });
        return new PrincipalDetail(user);
    }
}
