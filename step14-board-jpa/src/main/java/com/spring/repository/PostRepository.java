package com.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

  @Query(value = "select p from Post p join fetch p.member order by p.id desc", countQuery = "select count(p) from Post p")
  Page<Post> findAllWithPost(Pageable pageable);

  // Page<Post> searchWithPost(String keyword, Pageable pageable);

}
