package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.PostReaction;
import com.spring.entity.ReactionType;

public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {

  long countByPostIdAndType(Long postId, ReactionType type);

  Optional<PostReaction> findByMemberIdAndPostId(Long postId, Long memberId);

}
