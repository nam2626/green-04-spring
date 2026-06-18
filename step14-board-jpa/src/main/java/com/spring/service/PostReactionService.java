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


/**
 * [게시글 리액션(좋아요/싫어요) 비즈니스 로직 서비스 클래스]
 * 
 * @Transactional(readOnly = true):
 *   기본적으로 서비스 내 모든 메서드를 '읽기 전용 트랜잭션' 환경에서 구동시킵니다.
 *   조회만 수행하는 메서드에서 영속성 컨텍스트의 스냅샷 생성 및 변경 감지(Dirty Checking) 비용을 
 *   절약할 수 있어 조회 성능 최적화에 도움이 됩니다.
 * 
 * @Service:
 *   이 클래스가 비즈니스 로직을 수행하는 서비스 레이어의 컴포넌트(Spring Bean)임을 선언합니다.
 */
@Transactional(readOnly =  true)
@Service
public class PostReactionService {

  // JPA를 통해 데이터베이스에 접근할 수 있도록 각 리포지토리를 필드로 선언합니다.
  private PostReactionRepository postReactionRepository;
  private MemberRepository memberRepository;
  private PostRepository postRepository;

  /**
   * [생성자를 통한 의존성 주입 (Constructor Injection)]
   * 생성자 파라미터로 필요한 리포지토리 빈들을 전달받아 초기화합니다.
   * 스프링 프레임워크가 로딩 시점에 자동으로 의존 관계를 연결해 줍니다.
   */
  public PostReactionService(PostReactionRepository postReactionRepository, MemberRepository memberRepository,
      PostRepository postRepository) {
    this.postReactionRepository = postReactionRepository;
    this.memberRepository = memberRepository;
    this.postRepository = postRepository;
  }

  /**
   * [특정 게시글의 특정 리액션 타입(좋아요 또는 싫어요) 총 개수 조회]
   * 
   * @param postId 게시글의 식별 ID
   * @param type 리액션 구분 (LIKE 또는 DISLIKE)
   * @return 집계된 개수 (long 타입)
   */
  public long getReactionCount(Long postId, ReactionType type) {
    // 리포지토리에 선언된 count 메서드를 호출하여 결과를 바로 반환합니다.
    return postReactionRepository.countByPostIdAndType(postId,type);
  }

  /**
   * [게시글 좋아요/싫어요 추가 또는 변경 및 취소 처리 로직]
   * 
   * @Transactional:
   *   데이터 변경(INSERT, UPDATE, DELETE)이 수반되는 메서드이므로 클래스 레벨의 readOnly=true를 덮어쓰고,
   *   쓰기 작업이 가능한 트랜잭션을 적용합니다. 예외 발생 시 자동 롤백을 지원합니다.
   * 
   * @param postId 게시글 ID
   * @param reactionType 클릭한 리액션 종류 (LIKE / DISLIKE)
   * @param memberId 현재 로그인한 회원의 식별 ID
   */
  @Transactional
  public void addReaction(Long postId, ReactionType reactionType, Long memberId) {
    // 1. 해당 게시글(postId)에 대해 해당 회원(memberId)이 이전에 리액션을 한 적이 있는지 조회합니다.
    // 결과는 null 세이프하게 관리할 수 있도록 Java 8의 Optional 타입으로 수신합니다.
    Optional<PostReaction> opt = postReactionRepository.findByPostIdAndMemberId(postId,memberId);
    
    // 로그 출력을 통해 현재 상황을 콘솔에서 점검합니다. (opt.isEmpty()가 true이면 기존 리액션이 없는 상태)
    System.out.println(postId + " / " + memberId + " / " + opt.isEmpty());
    
    // 2. 기존에 누른 리액션 정보가 존재하는 경우
    if(!opt.isEmpty()){
      // Optional 내부에서 실제 엔티티 객체를 꺼냅니다. 데이터가 없다면 IllegalArgumentException 예외를 발생시킵니다.
      PostReaction reaction = opt.orElseThrow(() -> new IllegalArgumentException("해당 좋아요 싫어요 데이터가 없습니다."));
      
      // 2-a. 기존 리액션 타입과 새로 누른 리액션 타입이 동일한 경우 (예: 좋아요 상태에서 다시 좋아요 클릭)
      if(reaction.getType() == reactionType){
        // 취소(토글) 처리를 위해 데이터베이스에서 리액션 데이터를 영구히 제거합니다.
        postReactionRepository.delete(reaction);
      } else {
        // 2-b. 기존 리액션 타입과 새로 누른 리액션 타입이 다른 경우 (예: 좋아요 상태에서 싫어요 클릭)
        // JPA 영속성 컨텍스트에 의해, 엔티티 객체의 값만 변경해주면 트랜잭션이 끝날 때 DB에 UPDATE 쿼리가 전송됩니다.
        reaction.setType(reactionType); 
      }
    } 
    // 3. 기존에 누른 리액션 정보가 존재하지 않는 경우 (처음으로 좋아요 또는 싫어요 클릭)
    else {
      // 새로운 리액션 엔티티 객체를 생성합니다.
      PostReaction reaction = new PostReaction();
      
      // 관계 설정(외래키 바인딩)을 위해 회원 리포지토리에서 회원을 찾아서 세팅합니다.
      reaction.setMember(memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다.")));
      
      // 관계 설정(외래키 바인딩)을 위해 게시글 리포지토리에서 게시글을 찾아서 세팅합니다.
      reaction.setPost(postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다.")));
      
      // 전달받은 리액션 타입을 세팅합니다.
      reaction.setType(reactionType);
      
      // 리포지토리의 save()를 호출하여 데이터베이스에 신규 등록(INSERT)합니다.
      postReactionRepository.save(reaction);
    }
  }

}
