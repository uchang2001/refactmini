package com.one.miniproject.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

    private Long postid;
    private String username;
    private String content;

}