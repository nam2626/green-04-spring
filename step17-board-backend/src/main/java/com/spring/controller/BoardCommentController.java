package com.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.BoardCommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class BoardCommentController {

  private final BoardCommentService boardCommentService;

  //댓글 추가
  // 사용자 ID, 댓글 내용, 글번호
}
