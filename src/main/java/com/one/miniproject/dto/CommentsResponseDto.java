package com.one.miniproject.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentsResponseDto {
    private Long commentid;
    private String username;
    private String content;
    private LocalDateTime createAt;
}