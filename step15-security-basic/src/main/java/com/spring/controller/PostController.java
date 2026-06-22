package com.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
  private List<Map<String,Object>> postList = List.of(
    Map.of("id",1,"title","제목1","author","admin","createdAt","2026-01-01"),
    Map.of("id",2,"title","제목2","author","admin","createdAt","2026-01-02"),
    Map.of("id",3,"title","제목3","author","admin","createdAt","2026-01-03"),
    Map.of("id",4,"title","제목4","author","admin","createdAt","2026-01-04"));

    // 전체 게시글 목록 리턴하는 메서드
    @GetMapping
    public ResponseEntity<List<Map<String,Object>>> list(){
      return ResponseEntity.ok(postList);
    }
}