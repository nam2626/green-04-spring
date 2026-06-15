package com.spring.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.entity.Member;
import com.spring.entity.Post;
import com.spring.service.AttachmentService;
import com.spring.service.CommentService;
import com.spring.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class PostController {

  private final PostService postService;
  private final AttachmentService attachmentService;
  private final CommentService commentService;

  public PostController(PostService postService, AttachmentService attachmentService, CommentService commentService) {
    this.postService = postService;
    this.attachmentService = attachmentService;
    this.commentService = commentService;
  }

  @GetMapping
  public ModelAndView list(ModelAndView view) {
    // 전체 게시글 읽어와서 board/list.html로 이동
    List<Post> list = postService.getPostList();
    list.add(new Post(1L, "제목", "내용", new Member(1L, "사용자", "asdasd", "null", "asdasd", LocalDateTime.now()),
        300L, LocalDateTime.now(), LocalDateTime.now(), null, null));
    list.add(new Post(2L, "제목", "내용", new Member(1L, "사용자", "asdasd", "null", "asdasd", LocalDateTime.now()),
        300L, LocalDateTime.now(), LocalDateTime.now(), null, null));
    list.add(new Post(3L, "제목", "내용", new Member(1L, "사용자", "asdasd", "null", "asdasd", LocalDateTime.now()),
        300L, LocalDateTime.now(), LocalDateTime.now(), null, null));
    view.addObject("postPage", list);
    view.setViewName("board/list");
    return view;
  }

}
