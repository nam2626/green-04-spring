package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.spring.dto.CommentFormDTO;
import com.spring.entity.Member;
import com.spring.entity.Post;
import com.spring.service.CommentService;
import com.spring.service.PostService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RequestMapping("/comments")
@Controller
public class CommentController {
  private CommentService commentService;
  private PostService postService;

  public CommentController(CommentService commentService, PostService postService) {
    this.commentService = commentService;
    this.postService = postService;
  }

  @PostMapping("/post/{id}")
  public String addComment(@PathVariable Long id, @Valid CommentFormDTO form, BindingResult bindingResult, @SessionAttribute(value = "loginMember", required = false) Member member) {
    //로그인 안했을때 로그인 페이지로 이동  
    if(member == null) return "redirect:/auth/login";
    Post post = postService.findById(id);
    commentService.addComment(form, post, member);

      return "redirect:/board/"+id;
  }



  
  
}
