package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.ReactionDTO;
import com.spring.entity.Member;
import com.spring.entity.ReactionType;
import com.spring.service.PostReactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;


@RequestMapping("/reaction")
@RestController
public class ReactionController {

  private final PostReactionService postReactionService;

  public ReactionController(PostReactionService postReactionService) {
    this.postReactionService = postReactionService;
  }

  @GetMapping("/post/{postId}/{type}")
  public ReactionDTO postReaction(@PathVariable Long postId, @PathVariable String type, @SessionAttribute("loginMember") Member loginMember) {
      System.out.println(postId + " / " + type);
      ReactionType reactionType = ReactionType.valueOf(type.toUpperCase());
      postReactionService.addReaction(postId, reactionType, loginMember.getId());

      ReactionDTO reactionDTO = new ReactionDTO();
      //해당 게시글의 좋아요 값
      reactionDTO.setLikes(postReactionService.getReactionCount(postId, ReactionType.LIKE));
      //해당 게시글의 싫어요 값
      reactionDTO.setDislikes(postReactionService.getReactionCount(postId, ReactionType.DISLIKE));

      return reactionDTO;
  }
  
  
}
