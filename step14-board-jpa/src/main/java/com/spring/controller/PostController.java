package com.spring.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
  public ModelAndView list(ModelAndView view,
      @RequestParam(defaultValue = "") String keyword,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

    Pageable pageable = PageRequest.of(page, size);

    // 전체 게시글 읽어와서 board/list.html로 이동
    Page<Post> list = postService.getPostList(keyword, pageable);

    view.addObject("postPage", list);
    view.setViewName("board/list");
    return view;
  }

}
