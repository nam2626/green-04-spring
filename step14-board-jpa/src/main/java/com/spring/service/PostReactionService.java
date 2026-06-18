package com.spring.service;

import java.nio.file.OpenOption;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.PostReaction;
import com.spring.entity.ReactionType;
import com.spring.repository.MemberRepository;
import com.spring.repository.PostReactionRepository;
import com.spring.repository.PostRepository;



@Transactional(readOnly =  true)
@Service
public class PostReactionService {

  private PostReactionRepository postReactionRepository;
  private MemberRepository memberRepository;
  private PostRepository postRepository;

  public PostReactionService(PostReactionRepository postReactionRepository, MemberRepository memberRepository,
      PostRepository postRepository) {
    this.postReactionRepository = postReactionRepository;
    this.memberRepository = memberRepository;
    this.postRepository = postRepository;
  }

  public long getReactionCount(Long postId, ReactionType type) {
    return postReactionRepository.countByPostIdAndType(postId,type);
  }

  @Transactional
  public void addReaction(Long postId, ReactionType reactionType, Long memberId) {
   Optional<PostReaction> opt = postReactionRepository.findByMemberIdAndPostId(postId,memberId);

   if(opt.isPresent()){
      PostReaction reaction = opt.get();
      if(reaction.getType() == reactionType){
        postReactionRepository.delete(reaction);// 같은 타입이면 취소
      }else{
        reaction.setType(reactionType); // 다른 타입이면 타입 변경
      }
   }else{
      PostReaction reaction = new PostReaction();
      reaction.setMember(memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다.")));
      reaction.setPost(postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.")));
      reaction.setType(reactionType);
      postReactionRepository.save(reaction);
   }
  }

}
