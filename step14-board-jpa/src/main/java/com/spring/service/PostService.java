package com.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.entity.Post;
import com.spring.repository.PostRepository;

/**
 * [게시판 서비스 클래스]
 * 
 * @Service: 이 클래스가 서비스(비즈니스 로직) 레이어의 핵심 컴포넌트임을 명시하며 스프링 Bean으로 등록합니다.
 *           컨트롤러와 리포지토리(DB 접근) 사이에서 비즈니스적인 규칙이나 트랜잭션 처리를 관리합니다.
 */
@Service
public class PostService {

  // 데이터베이스에 접근하기 위한 JPA 리포지토리 의존성 주입 대상 필드입니다.
  private final PostRepository postRepository;

  /**
   * [생성자를 통한 의존성 주입]
   * 생성자 주입을 권장하는 이유는 불변성 확보, 누락 방지, 테스트 코드 작성 용이성 등이 있기 때문입니다.
   */
  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  /**
   * [전체 게시글 목록 단순 조회]
   * findAll(): JpaRepository 기본 제공 메서드로 전체 엔티티 데이터를 리스트로 가져옵니다.
   */
  public List<Post> getPostList() {
    return postRepository.findAll();
  }

  /**
   * [게시글 페이징 및 검색 조회]
   * 
   * @param keyword  검색어
   * @param pageable 페이징 정보 (현재 페이지 번호, 한 페이지 당 가져올 데이터 수 등)
   * @return Page<Post> 페이징 처리가 내포된 게시글 데이터 목록
   */
  public Page<Post> getPostList(String keyword, Pageable pageable) {
    // 검색어가 없는 경우 (기본 목록 조회)
    if (keyword == null || keyword.isEmpty()) {
      // PostRepository에 정의한 JPQL 페치 조인 쿼리를 실행하여 N+1 문제를 방지하며 데이터를 가져옵니다.
      return postRepository.findAllWithPost(pageable);
    } else {
      // TODO: 검색어가 존재하는 경우 검색 결과 목록 페이징 조회를 구현합니다.
      // return postRepository.searchWithPost(keyword, pageable);
      return null;
    }
  }

}
