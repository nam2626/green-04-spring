package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.entity.Post;
import com.spring.repository.PostRepository;

@Service
public class PostService {

  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getPostList() {
    return postRepository.findAll();
  }

}
