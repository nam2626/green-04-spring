package com.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 리액션 번호(Long, PK)
 * 회원아이디(Member, member_id)
 * 댓글 번호(Post, post_id)
 * 리액션 종류(ReactionType)
 * 
 */
@Entity
@Table(name = "comment_reaction", uniqueConstraints = @UniqueConstraint(columnNames = { "member_id", "comment_id" }))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "comment_id", nullable = false)
  private Comment comment;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 10)
  private ReactionType type;
}
