package com.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.UserEntity;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
  private List<Map<String,Object>> postList = new ArrayList<>(List.of(
    Map.of("id",1,"title","제목1","author","admin","createdAt","2026-01-01"),
    Map.of("id",2,"title","제목2","author","admin","createdAt","2026-01-02"),
    Map.of("id",3,"title","제목3","author","admin","createdAt","2026-01-03"),
    Map.of("id",4,"title","제목4","author","admin","createdAt","2026-01-04")));

    // 전체 게시글 목록 리턴하는 메서드 - 인증 X
    @GetMapping
    public ResponseEntity<List<Map<String,Object>>> list(){
      return ResponseEntity.ok(postList);
    }

    // 게시글 단건 조회 - 인증 X
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> post(@PathVariable int id){
      return ResponseEntity.ok(postList.get(id));
    }

    // 게시글 추가 - 인증 O
    // 현재 로그인한 사용자 주입
    // "게시글 등록 성공", 전체 게시글 정보 클라이언트로 전달
    @PostMapping
    public ResponseEntity<Map<String,Object>> addPost(@RequestBody Map<String, String> body,@AuthenticationPrincipal UserEntity user) {
      System.out.println(body);

        postList.add(Map.of("id", postList.size() + 1,"title",body.get("title"),
      "author",body.get("author"), "createdAt", body.get("createdAt")));
        System.out.println(postList);
        return ResponseEntity.ok(Map.of("message", "게시글 등록 성공","list", postList));
    }
    
}