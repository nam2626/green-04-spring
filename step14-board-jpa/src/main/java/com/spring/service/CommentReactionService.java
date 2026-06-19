package com.spring.service;

import com.spring.repository.CommentReactionRepository;
import com.spring.repository.CommentRepository;
import com.spring.repository.CommentReactionRepository.CommentReactionCount;
import com.spring.repository.MemberRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.dto.ReactionDTO;
import com.spring.entity.CommentReaction;
import com.spring.entity.PostReaction;
import com.spring.entity.ReactionType;

@Service
public class CommentReactionService {

  private final CommentReactionRepository commentReactionRepository;
  private final MemberRepository memberRepository;
  private final CommentRepository commentRepository;

  public CommentReactionService(CommentReactionRepository commentReactionRepository, MemberRepository memberRepository,
      CommentRepository commentRepository) {
    this.commentReactionRepository = commentReactionRepository;
    this.memberRepository = memberRepository;
    this.commentRepository = commentRepository;
  }

  public Map<Long, ReactionDTO> getCommentReactions(List<Long> commentIds) {
    Map<Long, ReactionDTO> commentReactions = new LinkedHashMap<>();

    Map<Long,Long> likes = new LinkedHashMap<>();
    Map<Long,Long> dislikes = new LinkedHashMap<>();

    // 반응이 없는 댓글고 화면에서는 0으로 출력할 수 있게 기본값을 셋팅
    commentIds.forEach(item -> {
      likes.put(item, 0L);
      dislikes.put(item, 0L);
    });

    // 댓글 ID 목록을 기준으로 좋아요, 싫어요 개수를 한번에 집계
    List<CommentReactionCount> reactionCounts = commentReactionRepository.countByCommentIdsGroupByType(commentIds);

    reactionCounts.forEach(item -> {
      if(item.getType() == ReactionType.LIKE){
        likes.put(item.getCommentId(), item.getCount());
      }else if(item.getType() == ReactionType.DISLIKE){
        dislikes.put(item.getCommentId(), item.getCount());
      }
    });

    commentIds.forEach(commentId ->{
      commentReactions.put(commentId, 
        new ReactionDTO(likes.get(commentId),dislikes.get(commentId),null));
    });

    return commentReactions;
  }

  public Long getCommentReaction(Long commentId, ReactionType type) {
    return commentReactionRepository.countByCommentIdAndType(commentId, type);
  }

  public void addReaction(Long commentId, ReactionType reactionType, Long memberId) {
   Optional<CommentReaction> opt = commentReactionRepository.findByCommentIdAndMemberId(commentId,memberId);

   if(!opt.isEmpty()){
      // Optional 내부에서 실제 엔티티 객체를 꺼냅니다. 데이터가 없다면 IllegalArgumentException 예외를 발생시킵니다.
      CommentReaction reaction = opt.orElseThrow(() -> new IllegalArgumentException("해당 좋아요 싫어요 데이터가 없습니다."));
      
      if(reaction.getType() == reactionType){
        commentReactionRepository.delete(reaction);
      } else {
        reaction.setType(reactionType); 
      }
    } 
    else {
      CommentReaction reaction = new CommentReaction();
      
      reaction.setMember(memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다.")));
      
      reaction.setComment(commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다.")));
      
      reaction.setType(reactionType);
      
      commentReactionRepository.save(reaction);
    }
  }

}
