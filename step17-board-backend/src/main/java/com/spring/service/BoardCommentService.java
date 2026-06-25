package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.mapper.BoardCommentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardCommentService {
  private final BoardCommentMapper boardCommentMapper;
}
