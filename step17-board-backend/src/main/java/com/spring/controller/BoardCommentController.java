package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.BoardCommentDTO;
import com.spring.dto.BoardDTO;
import com.spring.entity.UserEntity;
import com.spring.service.BoardCommentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class BoardCommentController {

  private final BoardCommentService boardCommentService;

  //댓글 추가
  // 사용자 ID, 댓글 내용, 글번호
  @PostMapping
  public ResponseEntity addBoardComment(
    @RequestBody BoardCommentDTO comment,
    @AuthenticationPrincipal UserEntity entity
  ) {
      comment.setMid(entity.getId());
      boardCommentService.addBoardComment(comment);

      return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
