package com.one.miniproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
public class detailDto {
    private long postId;
    private String title;
    private String content;
    private String[] imageSrc;
    @CreatedDate
    private LocalDateTime createdAt;
    private int good;
    private int star;
    private boolean heart;

}