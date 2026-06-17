package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  @Query("select c from Comment c JOIN FETCH c.member m where c.post.id = :postId order by c.createdAt ASC")
  List<Comment> getCommentByPost(@Param("postId") Long id);

}
