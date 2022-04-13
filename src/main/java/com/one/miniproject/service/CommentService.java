package com.one.miniproject.service;

import com.one.miniproject.dto.CommentDto;
import com.one.miniproject.dto.CommentsResponseDto;
import com.one.miniproject.dto.ResponseDto;
import com.one.miniproject.model.Comment;
import com.one.miniproject.model.Post;
import com.one.miniproject.model.User;
import com.one.miniproject.repository.CommentRepository;
import com.one.miniproject.repository.PostRepository;
import com.one.miniproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 해당 게시글에 따른 댓글 리스트 조회
    public List<CommentsResponseDto> comments(Long postid) {

        List<Comment> commentList  = commentRepository.findAllByPost_Postid(postid);
        List<CommentsResponseDto> commentsResponseDtoList = new ArrayList<>();

        for(Comment comment : commentList){
            CommentsResponseDto commentsResponseDto = new CommentsResponseDto();
            commentsResponseDto.setCommentid(comment.getCommentid());
            commentsResponseDto.setUsername(comment.getUser().getUsername());
            commentsResponseDto.setContent(comment.getContent());
            commentsResponseDto.setCreateAt(comment.getCreateAt());
            commentsResponseDtoList.add(commentsResponseDto);
        }



        return commentsResponseDtoList;
    }

    // 댓글 등록
    public ResponseDto createCommnet(CommentDto commentDto) {

        ResponseDto responseDto = new ResponseDto();
        Post post = postRepository.findById(commentDto.getPostid()).orElse(null);
        if(post == null){
            responseDto.setResult(false);
            responseDto.setErr_msg("해당 게시글이 존재 하지 않습니다");
            return  responseDto;
        }

        User user = userRepository.findByUsername(commentDto.getUsername()).orElse(null);

        if(user == null){
            responseDto.setResult(false);
            responseDto.setErr_msg("해당 회원이 존재 하지 않습니다");
            return  responseDto;
        }

        Comment comment = new Comment(post,user,commentDto.getContent());

        commentRepository.save(comment);

        responseDto.setResult(true);

        return  responseDto;
    }
}