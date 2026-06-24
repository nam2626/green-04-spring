package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
  private final BoardMapper boardMapper;

}
