package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.CommentFormDTO;
import com.spring.entity.Comment;
import com.spring.entity.Member;
import com.spring.entity.Post;
import com.spring.repository.CommentRepository;

@Transactional(readOnly = true)
@Service
public class CommentService {
  private final CommentRepository commentRepository;

  public CommentService(CommentRepository commentRepository) {
    this.commentRepository = commentRepository;
  }

  public List<Comment> getCommentByPost(Long id) {
    // return commentRepository.getCommentByPost(id);
    return commentRepository.findByPostIdOrderByCreatedAtAsc(id);
  }

  @Transactional
  public void addComment(CommentFormDTO form, Post post, Member member) {
    Comment comment = new Comment();
    comment.setContent(form.getContent());
    comment.setMember(member);
    comment.setPost(post);
    commentRepository.save(comment);
  }

  public Comment findById(Long id) {
    return commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당하는 댓글이 없습니다"));
  }

  @Transactional
  public void deleteById(Long id) {
    commentRepository.deleteById(id);
  }

}
