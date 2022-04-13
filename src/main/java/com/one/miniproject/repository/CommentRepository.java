
package com.one.miniproject.repository;

import com.one.miniproject.model.Comment;
import com.one.miniproject.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost_Postid(Long postid);

    void deleteAllByPost_Postid(Long postid);

}