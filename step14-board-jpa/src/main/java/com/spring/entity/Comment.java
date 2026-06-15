package com.spring.entity;

/**
 * 댓글번호(Long, PK)
 * 게시글번호(Many-to-One, FK)
 * 작성자(Member, Many-to-One, FK)
 * 내용(String, NotNull)
 * 작성일(LocalDateTime, NotNull, default = CURRENT_TIMESTAMP)
 */
public class Comment {

}
