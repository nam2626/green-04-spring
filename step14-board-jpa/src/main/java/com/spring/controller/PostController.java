package com.spring.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.spring.dto.PostFormDTO;
import com.spring.entity.Member;
import com.spring.entity.Post;
import com.spring.service.AttachmentService;
import com.spring.service.CommentService;
import com.spring.service.PostService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * [게시판 컨트롤러 클래스]
 * 
 * @Controller: 이 클래스가 Spring MVC의 컨트롤러 역할을 수행함을 나타내며, 스프링 컨테이너가 Bean으로 등록하여 관리합니다.
 * @RequestMapping("/board"): 이 컨트롤러 내부의 모든 핸들러 메서드들의 기본 URL 경로를 '/board'로 매핑합니다.
 */
@Controller
@RequestMapping("/board")
public class PostController {

  // 서비스 레이어와의 협업을 위한 의존성 주입 대상 필드들입니다.
  // final 키워드를 붙여 변경 불가능하게 하고 생성자 주입 방식으로 객체를 주입받습니다.
  private final PostService postService;
  private final AttachmentService attachmentService;
  private final CommentService commentService;

  /**
   * [생성자를 통한 의존성 주입(DI)]
   * Spring 4.3 이후부터는 생성자가 하나만 존재하고 생성자 파라미터가 빈(Bean)으로 등록되어 있다면,
   * 따로 @Autowired 어노테이션을 쓰지 않아도 스프링이 자동으로 주입해 줍니다.
   */
  public PostController(PostService postService, AttachmentService attachmentService, CommentService commentService) {
    this.postService = postService;
    this.attachmentService = attachmentService;
    this.commentService = commentService;
  }

  /**
   * [게시글 목록 화면 반환 API]
   * 
   * @GetMapping: HTTP GET 메서드로 '/board' 요청이 올 때 실행됩니다.
   * @RequestParam: 쿼리 스트링 파라미터를 받아옵니다. 파라미터가 넘어오지 않았을 경우 기본값(defaultValue)을 사용합니다.
   *   - keyword: 검색어 (기본값: 빈 문자열 "")
   *   - page: 현재 페이지 번호 (기본값: 0페이지부터 시작)
   *   - size: 한 페이지에 보여줄 게시글 수 (기본값: 10개)
   * 
   * ModelAndView: 화면(View) 이름과 화면에 전달할 데이터(Model)를 동시에 관리 및 반환해주는 스프링 제공 객체입니다.
   */
  @GetMapping
  public ModelAndView list(ModelAndView view,
      @RequestParam(defaultValue = "") String keyword,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {

    // PageRequest.of(page, size): 스프링 데이터의 페이징 정보를 나타내는 Pageable 객체를 생성합니다.
    // page는 0부터 시작하므로 첫 페이지는 0입니다.
    Pageable pageable = PageRequest.of(page, size);

    // postService를 통해 조건에 부합하는 페이징 처리된 게시글 정보(Page<Post>)를 얻어옵니다.
    Page<Post> list = postService.getPostList(keyword, pageable);
    
    // Page 객체에서 제공하는 유용한 페이징 관련 메서드들입니다. (로그 출력을 통해 작동 확인 가능)
    System.out.println("첫 페이지 여부: " + list.isFirst());
    System.out.println("마지막 페이지 여부: " + list.isLast());
    System.out.println("현재 페이지 번호 (0부터 시작): " + list.getNumber());
    System.out.println("한 페이지 당 데이터 수: " + list.getSize());
    System.out.println("전체 페이지 수: " + list.getTotalPages());
    
    // Thymeleaf 템플릿 엔진으로 데이터를 넘겨주기 위해 Model 객체에 attribute를 추가합니다.
    view.addObject("currentPage", page);
    view.addObject("postPage", list);
    view.addObject("keyword", keyword);
    
    // 이동할 뷰 이름을 'templates/board/list.html'로 매핑하기 위해 경로명을 입력합니다.
    view.setViewName("board/list");
    
    return view;
  }

  @GetMapping("/new")
  public String postForm(@SessionAttribute(value = "loginMember", required = false) Member loginMember, Model model) {
    System.out.println(loginMember);
    if(loginMember == null) return "redirect:/auth/login";

    model.addAttribute("form", new PostFormDTO());

      return "board/write";
  }
  
  @PostMapping("/new")
  public String postNew(@Valid @ModelAttribute("form") PostFormDTO form,
      BindingResult bindingResult,@SessionAttribute(value = "loginMember", required = false) Member loginMember, RedirectAttributes attributes,
      @RequestParam(value = "files", required = false) MultipartFile[] files
    ) throws IOException{
    
    if(loginMember == null) return "redirect:/auth/login";
    if(bindingResult.hasErrors()) return "board/write";

    Post post = postService.createPost(form,loginMember);
    attachmentService.saveFiles(files, post);
    // return "redirect:/board/"+post.getId();
    return "redirect:/";
  }

}
