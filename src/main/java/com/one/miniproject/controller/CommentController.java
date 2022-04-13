package com.one.miniproject.controller;

import com.one.miniproject.dto.CommentDto;
import com.one.miniproject.dto.CommentsResponseDto;
import com.one.miniproject.dto.ResponseDto;
import com.one.miniproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 해당 게시글에 따라 댓글 리스트 조회
    @GetMapping("/api/posts/comment/{postid}")
    public List<CommentsResponseDto> comments(@PathVariable Long postid){

        return commentService.comments(postid);
    }

    // 댓글 등록
    @PostMapping("api/posts/comment")
    public ResponseDto createComment(@RequestBody CommentDto commentDto){

        return commentService.createCommnet(commentDto);
    }

}
