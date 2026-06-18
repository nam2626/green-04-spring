package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.spring.dto.CommentFormDTO;
import com.spring.entity.Comment;
import com.spring.entity.Member;
import com.spring.entity.Post;
import com.spring.service.CommentService;
import com.spring.service.PostService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * [댓글 처리 컨트롤러 클래스]
 * 
 * @RequestMapping("/comments"): 
 *   이 컨트롤러에 정의된 모든 핸들러 메서드들의 기본 URL 경로를 '/comments'로 설정합니다.
 * @Controller: 
 *   이 클래스가 스프링 MVC의 뷰 기반 웹 컨트롤러임을 나타냅니다. 
 *   메서드가 문자열을 리턴할 때 기본적으로 리다이렉션 지시어("redirect:...") 또는 
 *   렌더링할 HTML 템플릿의 경로를 의미하게 됩니다.
 */
@RequestMapping("/comments")
@Controller
public class CommentController {
  
  // 댓글 처리 서비스와 게시글 조회를 위한 서비스를 필드로 정의합니다.
  private CommentService commentService;
  private PostService postService;

  /**
   * [생성자를 통한 의존성 주입 (Constructor Injection)]
   * 스프링이 실행될 때 두 서비스 컴포넌트를 주입하여 댓글 컨트롤러를 생성합니다.
   */
  public CommentController(CommentService commentService, PostService postService) {
    this.commentService = commentService;
    this.postService = postService;
  }

  /**
   * [신규 댓글 작성 등록 API]
   * 
   * @Valid CommentFormDTO form: 댓글 텍스트 내용의 비어있음 방지 등 유효성 조건을 검증합니다.
   * @SessionAttribute(value = "loginMember", required = false): 세션에서 로그인한 회원의 정보를 획득합니다. (없을 시 null)
   */
  @PostMapping("/post/{id}")
  public String addComment(
      @PathVariable Long id, 
      @Valid CommentFormDTO form, 
      BindingResult bindingResult, 
      @SessionAttribute(value = "loginMember", required = false) Member member) {
      
      // 1. 보안 검증: 로그인하지 않은 상태인 경우, 댓글 등록을 제한하고 로그인 페이지로 리다이렉트 처리합니다.
      if(member == null) return "redirect:/auth/login";
      
      // 2. 댓글을 달 타겟 게시글 정보를 DB에서 찾아 로드합니다.
      Post post = postService.findById(id);
      
      // 3. 댓글 서비스 레이어 호출: 댓글 내용, 타겟 게시글, 작성자 정보를 전달하여 DB 저장 처리를 진행합니다.
      commentService.addComment(form, post, member);

      // 4. 등록 후 상세페이지로 다시 리다이렉트합니다.
      // 뒤에 붙은 '#comments'는 브라우저가 리다이렉트된 화면을 보여줄 때 
      // id="comments" 요소(댓글 목록 구역) 위치로 화면 스크롤을 즉시 내려주도록 지정하는 HTML 앵커(Anchor) 기법입니다.
      return "redirect:/board/"+id +"#comments";
  }

  /**
   * [기존 댓글 삭제 API]
   */
  @GetMapping("/{id}/delete")
  public String delete(
      @PathVariable Long id, 
      @SessionAttribute(value = "loginMember", required = false) Member loginMember) {
      
      // 1. 삭제할 댓글 객체를 DB에서 가져옵니다.
      Comment comment = commentService.findById(id);
      
      // 2. 권한 검증: 로그인한 상태가 아니거나, 현재 로그인 사용자가 댓글 작성 본인이 아닌 경우 차단합니다.
      if(loginMember == null || loginMember.getId() != comment.getMember().getId() ){
        return "redirect:/auth/login";
      }

      // 3. 댓글 서비스를 호출하여 해당 댓글 레코드를 DB에서 제거합니다.
      commentService.deleteById(id);
      
      // 4. 댓글 삭제가 끝난 후, 댓글이 달려있던 원래 게시글 상세 화면으로 되돌려 보냅니다.
      return "redirect:/board/"+comment.getPost().getId();
  }
  
}
