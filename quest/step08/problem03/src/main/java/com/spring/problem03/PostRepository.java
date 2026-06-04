package com.spring.problem03;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 게시글 in-memory 저장소 [완성된 파일 — 수정 불필요]
 *
 * Repository 란?
 *   데이터를 저장하고 조회하는 역할을 담당하는 계층입니다.
 *   실제 서비스에서는 DB(MySQL, Oracle 등)와 연동하지만,
 *   이 예제에서는 List를 사용해 메모리에 데이터를 저장합니다.
 *
 * @Component : 스프링 컨테이너가 이 클래스를 Bean으로 관리합니다.
 *   → PostController의 생성자 주입 시 스프링이 자동으로 이 객체를 주입합니다.
 */
@Component
public class PostRepository {

    // 게시글을 저장하는 리스트 (메모리 저장소)
    private final List<Post> posts = new ArrayList<>();

    /**
     * AtomicLong : 멀티 스레드 환경에서 안전하게 증가하는 정수값을 제공합니다.
     * 일반 long++ 은 동시 접근 시 중복 ID가 생길 수 있지만,
     * AtomicLong.getAndIncrement()는 원자적(atomic)으로 동작하여 안전합니다.
     */
    private final AtomicLong idGen = new AtomicLong(1); // 1부터 시작하는 ID 생성기

    // 저장소 생성 시 샘플 데이터 3건을 미리 저장합니다.
    public PostRepository() {
        save(new Post(null, "첫 번째 게시글", "내용입니다.", "admin"));
        save(new Post(null, "두 번째 게시글", "내용 두 번째.", "user01"));
        save(new Post(null, "세 번째 게시글", "내용 세 번째.", "user02"));
    }

    /**
     * 게시글 저장 (Create)
     * id가 null인 새 게시글에 자동 증가 id를 부여하고 리스트에 추가합니다.
     *
     * @param post 저장할 게시글 객체 (id는 null로 전달)
     * @return     id가 부여된 게시글 객체
     */
    public Post save(Post post) {
        post.setId(idGen.getAndIncrement()); // 현재 값을 반환하고 1 증가
        posts.add(post);
        return post;
    }

    /**
     * 전체 게시글 목록 조회 (Read All)
     * 원본 리스트 대신 복사본을 반환하여 외부에서 리스트를 수정하지 못하게 합니다.
     *
     * @return 게시글 목록의 복사본
     */
    public List<Post> findAll() {
        return new ArrayList<>(posts); // 방어적 복사(defensive copy)
    }

    /**
     * 특정 id의 게시글 조회 (Read One)
     * Stream API로 id가 일치하는 첫 번째 게시글을 찾아 반환합니다.
     *
     * @param id 찾을 게시글의 id
     * @return   게시글 객체, 없으면 null
     */
    public Post findById(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id)) // id가 일치하는 게시글 필터링
                .findFirst()
                .orElse(null); // 없으면 null 반환
    }

    /**
     * 게시글 삭제 (Delete)
     * 리스트에서 id가 일치하는 항목을 제거합니다.
     *
     * removeIf(조건) : 조건이 true인 항목을 리스트에서 제거합니다.
     *
     * @param id 삭제할 게시글의 id
     * @return   삭제 성공 여부 (true: 삭제됨, false: 해당 id 없음)
     */
    public boolean deleteById(Long id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }
}
