package com.example.board_project.controller;

import com.example.board_project.config.auth.PrincipalDetail;
import com.example.board_project.dto.UserDto;
import com.example.board_project.dto.UserSelectDto;
import com.example.board_project.entity.OAuthToken;
import com.example.board_project.entity.User;
import com.example.board_project.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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
        UserSelectDto userSelectDto = userService.takeLoginUser(principalDetail);
        model.addAttribute("loginUser", userSelectDto);
        return "/user/detail";
    }

    @GetMapping("/user/modifyForm")
    public String modifyForm(Model model, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        UserSelectDto userSelectDto = userService.takeLoginUser(principalDetail);
        model.addAttribute("loginUser", userSelectDto);
        return "/user/modifyForm";
    }

    //이해를 위해서 controller의 하나의 함수에 모든 과정을 넣었지만 사실은 service에 나눠서 기능을 구현한다.
    @GetMapping("/auth/kakao/callback")
    @ResponseBody
    public String kakaoCallback(@RequestParam String code) throws JsonProcessingException {

        RestTemplate restTemplate = new RestTemplate();
        //HttpHeader 오브젝트 생성
        HttpHeaders httpHeaders = new HttpHeaders();
        //Content-type 을 HttpHeader에 담는다는 것은 내가 담을 데이터가 key-value 데이터라고 알려주는 것이다
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //httpBody 생성 부분
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "20c66978b03bcfb37f3f31f7a06a2488");
        params.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        params.add("code", code);
        //HttpHeader와 Httpbody를 하나의 오브젝트에 담김
        //restTemplate.exchange() 함수가 HttpEntity를 파라미터로 받기 때문
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, httpHeaders);
        //Http 요청하기 - POST방식 그리고 response 변수의 응답을 받음
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",  //요청 주소
                HttpMethod.POST,    //요청방법
                kakaoTokenRequest,  //넘기는 데이터
                String.class    //받아올 데이터 타입
        );
        //응답받은 responseEntity 를 살펴보면 Body에는 JSON 형태로 데이터가 들어있는데 이 JSON 형태의 데이터를 자바 오브젝트 형태로 변환해서 사용하기위해서 변환을 해준다.
        //ObjectMapper 클래스를 사용해서 변환
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oAuthToken = objectMapper.readValue(responseEntity.getBody(), OAuthToken.class);


        RestTemplate restTemplate2 = new RestTemplate();
        //HttpHeader 오브젝트 생성
        HttpHeaders httpHeaders2 = new HttpHeaders();
        //Content-type 을 HttpHeader에 담는다는 것은 내가 담을 데이터가 key-value 데이터라고 알려주는 것이다
        httpHeaders2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        httpHeaders2.add("Authorization","Bearer "+ oAuthToken.getAccess_token());

        //httpBody 생성 부분
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest =
                new HttpEntity<>(httpHeaders2);
        //Http 요청하기 - POST방식 그리고 response 변수의 응답을 받음
        ResponseEntity<String> responseEntity2 = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",  //요청 주소
                HttpMethod.POST,    //요청방법
                kakaoProfileRequest,  //넘기는 데이터
                String.class    //받아올 데이터 타입
        );
        //응답받은 responseEntity 를 살펴보면 Body에는 JSON 형태로 데이터가 들어있는데 이 JSON 형태의 데이터를 자바 오브젝트 형태로 변환해서 사용하기위해서 변환을 해준다.
        //ObjectMapper 클래스를 사용해서 변환

        return ""+responseEntity2;
    }
}