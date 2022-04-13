package com.one.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor // 생성자 자동으로 해줌
@Getter
public class KakaoUserInfoDto {
    private String username;
    private String nickname;

}