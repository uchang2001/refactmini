package com.one.miniproject.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.one.miniproject.dto.KakaoTokenDto;
import com.one.miniproject.dto.ResponseDto;
import com.one.miniproject.dto.SignUpRequestDto;
import com.one.miniproject.security.UserDetailsImpl;
import com.one.miniproject.service.KakaoUserService;
import com.one.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    private  final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService) {
        this.userService = userService;
        this.kakaoUserService = kakaoUserService;
    }

    // 회원가입
    @PostMapping("/user/signup")
    public ResponseDto signup(@RequestBody SignUpRequestDto requestDto) {
        return userService.registerUser(requestDto);
    }

    // 닉네임 중복 검사 - 모달
    @PostMapping("/user/nicknameCheck")
    public ResponseDto nicknameCheck(@RequestBody SignUpRequestDto requestDto) {
        return userService.nicknameCheck(requestDto);
    }

    // 카카오 로그인 인증 코드 전달
    @PostMapping("/user/kakao/callback")
    public ResponseDto kakaoLogin(@RequestBody KakaoTokenDto kakaoTokenDto) throws JsonProcessingException {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        return kakaoUserService.kakaoLogin(kakaoTokenDto.getCode());
    }

}
