package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.ReactionType;
import com.spring.repository.PostReactionRepository;

@Service
public class PostReactionService {

  @Autowired
  private PostReactionRepository postReactionRepository;

  public long getReactionCount(Long postId, ReactionType type) {
    return postReactionRepository.countByPostIdAndType(postId,type);
  }

}
