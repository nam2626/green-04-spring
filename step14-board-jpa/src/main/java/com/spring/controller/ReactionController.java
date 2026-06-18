package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.ReactionDTO;
import com.spring.entity.Member;
import com.spring.entity.ReactionType;
import com.spring.service.PostReactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * [게시글 리액션(좋아요/싫어요) REST 컨트롤러]
 * 
 * @RestController: 
 *   이 클래스가 HTTP 요청을 처리하고 JSON 또는 XML 형식으로 데이터를 직접 응답하는 
 *   RESTful 웹 서비스의 컨트롤러임을 선언합니다. 일반 @Controller와 달리 
 *   뷰(HTML)를 반환하지 않고, 리턴하는 객체 데이터를 JSON 바디로 변환해 응답합니다.
 * @RequestMapping("/reaction"): 
 *   이 컨트롤러 내의 모든 URL 요청 경로의 공통 접두사(Prefix)를 '/reaction'으로 지정합니다.
 */
@RequestMapping("/reaction")
@RestController
public class ReactionController {

  // 리액션 비즈니스 로직을 담당하는 서비스 레이어 객체를 주입받기 위한 필드입니다.
  // final 선언을 통해 한번 초기화된 이후 변경할 수 없도록 보장합니다.
  private final PostReactionService postReactionService;

  /**
   * [생성자 주입 (Constructor Injection)]
   * 스프링 컨테이너가 이 컨트롤러 객체를 만들 때, 이미 스프링 빈으로 등록된 PostReactionService 객체를 
   * 파라미터로 넘겨주어 의존성을 주입해 줍니다. 생성자 주입은 불변성과 테스트 편리성을 제공하여 권장됩니다.
   */
  public ReactionController(PostReactionService postReactionService) {
    this.postReactionService = postReactionService;
  }

  /**
   * [게시글 좋아요/싫어요 토글 처리 및 최신 개수 반환 API]
   * 
   * @GetMapping("/post/{postId}/{type}"): 
   *   HTTP GET 요청 중 '/reaction/post/{postId}/{type}' 형태의 요청을 처리합니다.
   * 
   * @PathVariable: 
   *   URL 경로에 포함된 변수 값을 자바 메서드 파라미터로 매핑합니다. 
   *   예: /reaction/post/15/like -> postId = 15, type = "like"
   * 
   * @SessionAttribute(value = "loginMember"): 
   *   HTTP 세션에 'loginMember'라는 이름으로 저장된 로그인 사용자 객체를 직접 파라미터로 바인딩받습니다.
   *   리액션을 표시하기 위해서는 로그인한 회원의 정보(회원 식별 ID 등)가 필요하므로 세션에서 정보를 꺼내옵니다.
   */
  @GetMapping("/post/{postId}/{type}")
  public ReactionDTO postReaction(
      @PathVariable Long postId, 
      @PathVariable String type, 
      @SessionAttribute("loginMember") Member loginMember) {
      
      // 로그 출력을 통해 클라이언트로부터 넘어온 게시글 ID와 리액션 타입(like 또는 dislike)을 확인합니다.
      System.out.println(postId + " / " + type);
      
      // 문자열로 넘어온 리액션 타입(예: "like")을 소문자/대문자 구분 없이 대문자로 통일하여 
      // Enum 타입(ReactionType.LIKE 또는 ReactionType.DISLIKE)으로 변환합니다.
      ReactionType reactionType = ReactionType.valueOf(type.toUpperCase());
      
      // 서비스 레이어를 호출해 해당 회원이 이 게시글에 대해 지정한 리액션 처리를 수행하게 합니다.
      // (기존 리액션이 없으면 추가, 동일한 리액션이 이미 있으면 취소, 다른 리액션이 있으면 변경)
      postReactionService.addReaction(postId, reactionType, loginMember.getId());

      // 클라이언트(화면)에 리액션 반영 후의 최신 좋아요/싫어요 총 개수를 JSON 형태로 응답하기 위해 DTO 객체를 생성합니다.
      ReactionDTO reactionDTO = new ReactionDTO();
      
      // DB에서 리액션이 적용된 후의 해당 게시글의 최신 '좋아요' 총 개수를 가져와 DTO에 세팅합니다.
      reactionDTO.setLikes(postReactionService.getReactionCount(postId, ReactionType.LIKE));
      
      // DB에서 리액션이 적용된 후의 해당 게시글의 최신 '싫어요' 총 개수를 가져와 DTO에 세팅합니다.
      reactionDTO.setDislikes(postReactionService.getReactionCount(postId, ReactionType.DISLIKE));

      // 세팅이 완료된 DTO 객체를 리턴합니다. @RestController 환경이므로 이 객체는 JSON {"likes": X, "dislikes": Y} 형태로 자동 변환되어 클라이언트에 전송됩니다.
      return reactionDTO;
  }
  
}
