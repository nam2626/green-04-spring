package com.spring.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReactionDTO {
  private Long likes;
  private Long dislikes;
  private String myReaction;
}
