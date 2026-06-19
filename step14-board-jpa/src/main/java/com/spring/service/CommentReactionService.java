package com.spring.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.spring.dto.ReactionDTO;

@Service
public class CommentReactionService {

  public Map<Long, ReactionDTO> getCommentReactions(List<Long> commentIds) {
    Map<Long, ReactionDTO> commentReactions = new LinkedHashMap<>();

    Map<Long,Long> likes = new LinkedHashMap<>();
    Map<Long,Long> dislikes = new LinkedHashMap<>();

    // 반응이 없는 댓글고 화면에서는 0으로 출력할 수 있게 기본값을 셋팅
    commentIds.forEach(item -> {
      likes.put(item, 0L);
      dislikes.put(item, 0L);
    });

    // 댓글 ID 목록을 기준으로 좋아요, 싫어요 개수를 한번에 집계
    

    return commentReactions;
  }

}
