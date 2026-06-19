package com.spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.entity.CommentReaction;
import com.spring.entity.ReactionType;

public interface CommentReactionRepository extends JpaRepository<CommentReaction, Long> {

    @Query("select cr.comment.id as commentId, cr.type as type, count(cr) as count from CommentReaction cr "
      + "where cr.comment.id IN :commentIds "
      + "group by cr.comment.id, cr.type" 
    )
    List<CommentReactionCount> countByCommentIdsGroupByType(@Param("commentIds") List<Long> commentIds);

    public interface CommentReactionCount {
      Long getCommentId();
      ReactionType getType();
      long getCount();      
    }

    Optional<CommentReaction> findByCommentIdAndMemberId(Long commentId, Long memberId);    

    long countByCommentIdAndType(Long CommentId, ReactionType type);

}
