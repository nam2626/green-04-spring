package com.spring.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * [리액션(좋아요/싫어요) 데이터 전송용 DTO 클래스]
 * 
 * DTO(Data Transfer Object)란?
 *   - 계층 간(예: Controller와 View/클라이언트 화면) 데이터 교환을 위해 사용하는 순수한 객체입니다.
 *   - 데이터베이스 엔티티를 화면으로 바로 넘길 경우 보안상 위험이나 불필요한 데이터 포함 문제가 있어,
 *     특정 화면이나 API 규격에 맞는 커스텀 DTO를 설계하여 사용하는 것이 표준적인 설계 기법입니다.
 * 
 * Lombok 어노테이션:
 *   - @Getter, @Setter: 클래스 선언부에 작성하면 Lombok 컴파일러가 각 필드들의 
 *     getter(getLikes() 등) 및 setter(setLikes() 등) 메서드를 자동으로 생성해 줍니다.
 */
@Getter
@Setter
public class ReactionDTO {

  // 해당 게시글에 대한 총 '좋아요' 누적 개수
  private Long likes;
  
  // 해당 게시글에 대한 총 '싫어요' 누적 개수
  private Long dislikes;
  
  // 현재 접속한 회원 자신이 해당 게시글에 취한 리액션 상태 정보
  private String myReaction;
}
