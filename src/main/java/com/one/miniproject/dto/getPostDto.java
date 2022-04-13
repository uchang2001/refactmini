package com.one.miniproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class) // 생성/수정 시간을 자동으로 반영하도록 설정
public class getPostDto {
    private long postId;
    private String title;
    private String content;
    private String[] imageSrc;
    @CreatedDate
    private LocalDateTime createdAt;
    private int good;
    private int star;


}