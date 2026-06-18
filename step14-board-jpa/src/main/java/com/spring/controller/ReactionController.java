package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.entity.ReactionType;
import com.spring.service.PostReactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/reaction")
@RestController
public class ReactionController {

  private final PostReactionService postReactionService;

  public ReactionController(PostReactionService postReactionService) {
    this.postReactionService = postReactionService;
  }

  @GetMapping("/post/{postId}/{type}")
  public String postReaction(@PathVariable Long postId, @PathVariable String type) {
      System.out.println(postId + " / " + type);
      return "안녕하세요";
  }
  
  
}
