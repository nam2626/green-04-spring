package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Comment;

public interface CommentReactionRepository extends JpaRepository<Comment, Long> {

}
