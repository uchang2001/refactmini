package com.one.miniproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class modifyDto {
    private long postid;
    private String title;
    private String content;
    private int star;
    private String username;
}