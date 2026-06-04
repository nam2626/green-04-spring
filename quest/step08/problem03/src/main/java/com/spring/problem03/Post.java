package com.spring.problem03;

/**
 * 게시글 도메인 객체 [완성된 파일 — 수정 불필요]
 *
 * 도메인 객체(Domain Object) 란?
 *   비즈니스 영역의 개념을 코드로 표현한 객체입니다.
 *   여기서는 "게시글(Post)" 이라는 개념을 Java 클래스로 나타냅니다.
 *
 * DTO 와의 차이:
 *   DTO는 데이터 전달만을 목적으로 하지만,
 *   도메인 객체는 비즈니스 로직을 가질 수 있습니다.
 *   이 예제에서는 단순함을 위해 비즈니스 로직 없이 데이터 구조만 정의합니다.
 */
public class Post {

    private Long id;       // 게시글 고유 번호 (DB의 PK에 해당)
    private String title;  // 게시글 제목
    private String content;// 게시글 본문 내용
    private String author; // 작성자 이름

    // 스프링이 폼 데이터를 객체로 바인딩할 때 기본 생성자를 사용합니다.
    public Post() {}

    // 저장소(Repository) 등에서 편리하게 객체를 생성할 때 사용합니다.
    public Post(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // --- Getter/Setter ---
    // 스프링 MVC의 폼 데이터 바인딩과 JSP EL(${post.title}) 사용을 위해 필요합니다.

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
}
