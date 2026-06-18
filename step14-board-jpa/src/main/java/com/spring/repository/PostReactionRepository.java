package com.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.PostReaction;
import com.spring.entity.ReactionType;

/**
 * [게시글 리액션 데이터 접근 리포지토리 인터페이스]
 * 
 * JpaRepository<PostReaction, Long> 상속:
 *   - JpaRepository를 상속하면, 기본적인 CRUD(등록, 수정, 삭제, 상세 조회, 전체 조회 등)
 *     메서드가 컴파일 시점에 자동으로 생성 및 주입됩니다.
 *   - 제네릭 타입의 첫 번째 파라미터(PostReaction)는 대상 엔티티 클래스이고,
 *     두 번째 파라미터(Long)는 그 엔티티의 기본키(@Id)의 자바 데이터 타입입니다.
 */
public interface PostReactionRepository extends JpaRepository<PostReaction, Long> {

  /**
   * [특정 게시글의 특정 리액션 타입(좋아요/싫어요)의 총 개수 집계 쿼리 메서드]
   * 
   * 스프링 데이터 JPA의 '쿼리 메서드(Query Method)' 메커니즘을 사용합니다.
   * 메서드 이름 규칙에 따라 내부적으로 다음과 같은 SQL 쿼리가 자동으로 조립됩니다.
   * SELECT COUNT(*) FROM post_reaction WHERE post_id = ? AND type = ?
   * 
   * @param postId 게시글의 ID (외래키 매핑)
   * @param type 리액션 타입 (LIKE, DISLIKE)
   * @return 집계된 개수
   */
  long countByPostIdAndType(Long postId, ReactionType type);

  /**
   * [특정 회원 및 특정 게시글 조건에 해당하는 단일 리액션 정보 조회]
   * 
   * 메서드 이름을 분석하여 스프링 데이터 JPA가 자동으로 아래의 SQL 쿼리를 생성합니다.
   * SELECT * FROM post_reaction WHERE post_id = ? AND member_id = ?
   * 
   * @param postId 게시글의 ID
   * @param memberId 회원의 ID
   * @return 조회 결과를 담은 Optional 객체 (조회 결과가 없을 경우 대비)
   */
  Optional<PostReaction> findByPostIdAndMemberId(Long postId, Long memberId);

}
