package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.dto.BoardCommentDTO;
import com.spring.mapper.BoardCommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardCommentService {
  private final BoardCommentMapper boardCommentMapper;

  public void addBoardComment(BoardCommentDTO comment) {
    boardCommentMapper.insertBoardComment(comment);
  }
}
