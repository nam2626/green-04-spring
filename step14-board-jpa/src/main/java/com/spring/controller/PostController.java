package com.spring.controller;

import java.io.IOException;
import java.util.List;

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

import com.spring.dto.CommentFormDTO;
import com.spring.dto.PostFormDTO;
import com.spring.entity.Attachment;
import com.spring.entity.Comment;
import com.spring.entity.Member;
import com.spring.entity.Post;
import com.spring.service.AttachmentService;
import com.spring.service.CommentService;
import com.spring.service.PostService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

  /**
   * [게시글 작성 화면 반환 API]
   * 
   * @SessionAttribute(value = "loginMember", required = false): 
   *   세션에 저장되어 있는 "loginMember" 속성값(로그인 회원 정보)을 바로 파라미터로 바인딩받습니다.
   *   - required = false: 비로그인 상태(세션에 해당 속성이 없음)여도 예외를 내지 않고 null로 받아옵니다.
   * 
   * @param loginMember 세션에서 꺼내온 현재 로그인 회원 객체 (없다면 null)
   * @param model 화면에 DTO를 넘겨줄 모델 객체
   * @return 로그인하지 않았다면 로그인 페이지로 리다이렉트, 로그인 상태라면 글쓰기 화면("templates/board/write.html") 반환
   */
  @GetMapping("/new")
  public String postForm(@SessionAttribute(value = "loginMember", required = false) Member loginMember, Model model) {
    // 세션 정보가 없으면 글쓰기를 제한하고 로그인 화면으로 리다이렉트 처리합니다.
    if(loginMember == null) return "redirect:/auth/login";

    // 폼 검증과 입력 데이터 보관을 위해 빈 PostFormDTO 객체를 뷰에 전달합니다.
    model.addAttribute("form", new PostFormDTO());

    return "board/write";
  }
  
  /**
   * [신규 게시글 등록 및 파일 업로드 처리 API]
   * 
   * @Valid @ModelAttribute("form") PostFormDTO form: 
   *   화면 폼에서 넘어온 제목/본문 텍스트를 DTO에 바인딩하고 유효성 제약조건(@NotBlank 등)을 즉시 검사합니다.
   * @BindingResult bindingResult: 
   *   검증 실패 정보가 담기는 객체입니다.
   * @RequestParam(value = "files", required = false): 
   *   HTML input[type="file"] 태그의 name="files"로 전송된 업로드 파일 목록을 수신합니다.
   */
  @PostMapping("/new")
  public String postNew(@Valid @ModelAttribute("form") PostFormDTO form,
      BindingResult bindingResult,
      @SessionAttribute(value = "loginMember", required = false) Member loginMember, 
      RedirectAttributes attributes,
      @RequestParam(value = "files", required = false) MultipartFile[] files
    ) throws IOException {
    
    // 1. 비로그인 접근 차단
    if(loginMember == null) return "redirect:/auth/login";
    
    // 2. 글 제목/내용 입력 오류가 있다면 글 작성 화면으로 백(Back) 처리
    if(bindingResult.hasErrors()) return "board/write";

    // 3. 서비스 레이어를 호출하여 게시글 본문 DB 저장
    Post post = postService.createPost(form, loginMember);
    
    // 4. 서비스 레이어를 호출하여 첨부파일(들) 디스크 복사 및 메타데이터 DB 저장
    attachmentService.saveFiles(files, post);
    
    // 5. 등록 완료 후 홈 화면("/")으로 이동 (이후 /board로 다시 리다이렉트 됨)
    return "redirect:/";
  }

  @GetMapping("/{id}")
  public ModelAndView detail(@PathVariable Long id, ModelAndView view) {
      Post post = postService.findById(id);
      // post.getComments().forEach(comment ->{
      //   System.out.println(comment.getId() + " / " + comment.getContent());
      // });
      // 댓글 목록 조회
      List<Comment> comments = commentService.getCommentByPost(id);
      for (Comment comment : comments) {
        System.out.println(comment.getId() + " / " + comment.getContent());
      }
      // 첨부 파일 목록 조회
      List<Attachment> attachments = attachmentService.getAttachmentByPost(id);
      for (Attachment attachment : attachments) {
        System.out.println(attachment.getOriginalName());
      }
      view.addObject("comments", comments);
      view.addObject("attachments", attachments);
      view.addObject("post", post);
      view.addObject("commentForm", new CommentFormDTO());
      view.setViewName("board/detail");
      return view;
  }
  
}
