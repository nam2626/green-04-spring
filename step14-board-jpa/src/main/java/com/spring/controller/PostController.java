package com.spring.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
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
import com.spring.dto.ReactionDTO;
import com.spring.entity.Attachment;
import com.spring.entity.Comment;
import com.spring.entity.Member;
import com.spring.entity.Post;
import com.spring.entity.ReactionType;
import com.spring.service.AttachmentService;
import com.spring.service.CommentReactionService;
import com.spring.service.CommentService;
import com.spring.service.PostReactionService;
import com.spring.service.PostService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestBody;


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
  private final PostReactionService postReactionService;
  private final CommentReactionService commentReactionService;


  /**
   * [생성자를 통한 의존성 주입(DI)]
   * Spring 4.3 이후부터는 생성자가 하나만 존재하고 생성자 파라미터가 빈(Bean)으로 등록되어 있다면,
   * 따로 @Autowired 어노테이션을 쓰지 않아도 스프링이 자동으로 주입해 줍니다.
   */
   public PostController(PostService postService, AttachmentService attachmentService, CommentService commentService,
      PostReactionService postReactionService, CommentReactionService commentReactionService) {
    this.postService = postService;
    this.attachmentService = attachmentService;
    this.commentService = commentService;
    this.postReactionService = postReactionService;
    this.commentReactionService = commentReactionService;
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

  /**
   * [게시글 상세 정보 조회 화면 API]
   * 
   * @PathVariable Long id: URL 상에서 넘어오는 게시글의 고유 PK 값을 바인딩받습니다.
   * @ModelAttribute("page") Integer page: 
   *   목록에서 상호작용한 이전 페이지 번호를 보관하여, 수정이나 취소 클릭 시 목록의 해당 페이지로 
   *   안전하게 돌아갈 수 있도록 컨트롤러 모델 데이터로 바인딩해둡니다.
   * @param session: 동일 사용자의 중복 조회수 증가 방지를 위해 서블릿 세션(Session) 객체를 획득합니다.
   */
  @GetMapping("/{id}")
  public ModelAndView detail(@PathVariable Long id, @ModelAttribute("page")  Integer page,
     ModelAndView view, HttpSession session) {
      
      // 1. 게시글 정보를 ID로 조회합니다. 존재하지 않는다면 내부적으로 예외 처리가 발생합니다.
      Post post = postService.findById(id);

      // 2. 해당 게시글에 소속된 댓글 리스트 전체를 DB에서 조회합니다.
      List<Comment> comments = commentService.getCommentByPost(id);
      for (Comment comment : comments) {
        System.out.println(comment.getId() + " / " + comment.getContent());
      }
      
      // 3. 해당 게시글에 연결된 첨부파일 리스트 전체를 DB에서 조회합니다.
      List<Attachment> attachments = attachmentService.getAttachmentByPost(id);
      for (Attachment attachment : attachments) {
        System.out.println(attachment.getOriginalName());
      }
      
      // 4. [조회수 중복 증가 방지 로직]
      // 사용자가 본 글의 ID 목록을 보관할 Set 컬렉션을 세션에서 가져옵니다.
      HashSet<Long> pageList = (HashSet<Long>) session.getAttribute("pageList"); 

      // 세션에 방문 기록 집합(pageList)이 아직 존재하지 않는 경우 (첫 상세페이지 방문 시)
      if(pageList == null){
        pageList = new HashSet<Long>();
        session.setAttribute("pageList", pageList);
      }

      // HashSet의 add() 메서드는 중복되지 않은 값을 추가할 때만 true를 반환합니다.
      // 즉, 처음 방문한 글 번호인 경우 true가 되어 조회수 카운트가 1 증가하고, 새로고침 시에는 false가 되어 증가하지 않습니다.
      if(pageList.add(id)){
        postService.updateCount(id);
      }

      // 5. [게시글 좋아요/싫어요 개수 계산]
      // 서비스 레이어의 count 쿼리를 호출해 좋아요와 싫어요 총합을 개별적으로 가져옵니다.
      long postLikes = postReactionService.getReactionCount(id,ReactionType.LIKE);
      long postDislikes = postReactionService.getReactionCount(id,ReactionType.DISLIKE);
      System.out.println(postLikes + " / " + postDislikes);
      
      // 화면에 데이터를 보내기 위해 DTO 객체에 조회한 좋아요/싫어요 개수를 세팅합니다.
      ReactionDTO postReaction = new ReactionDTO();
      postReaction.setLikes(postLikes);
      postReaction.setDislikes(postDislikes);
      
      // 6. Thymeleaf 템플릿 뷰 엔진에서 활용할 변수들을 ModelAndView 객체에 저장합니다.
      view.addObject("comments", comments);
      view.addObject("attachments", attachments);
      view.addObject("post", post);
      // 신규 댓글 작성을 위한 검증 폼 바인딩 객체를 빈 DTO로 제공합니다.
      view.addObject("commentForm", new CommentFormDTO());
      view.addObject("postReaction", postReaction);
      
      // 렌더링할 화면 이름('templates/board/detail.html')을 설정합니다.
      view.setViewName("board/detail");
      return view;
  }

  /**
   * [게시글 및 관련 첨부파일 완전 삭제 API]
   * 
   * @Value("${app.upload.dir}"): application.properties 설정에 정의된 업로드 폴더의 로컬 물리 경로를 주입받습니다.
   */
  @GetMapping("/{id}/delete")
  public String delete(@PathVariable Long id,@SessionAttribute(value = "loginMember", required = false)Member loginMember, @Value("${app.upload.dir}") String uploadDir) {
      Post post = postService.findById(id);
      
      // 1. 보안 검증: 로그인 상태가 아니거나, 현재 로그인한 사용자가 게시글 작성자가 아니라면 차단합니다.
      if(loginMember == null || loginMember.getId() != post.getMember().getId() ){
        return "redirect:/auth/login";
      }
      
      // 2. 물리적 첨부파일 삭제
      // 데이터베이스에 등록된 파일 정보를 읽어온 다음, 업로드 디렉토리 내의 실제 파일을 하드디스크에서 영구 삭제합니다.
      List<Attachment> fileList = attachmentService.getAttachmentByPost(id);
      Path rootPath = Paths.get(uploadDir).toAbsolutePath();
      for (Attachment att : fileList) {
        // resolve()를 통해 '업로드디렉토리경로/서버보관용파일명' 경로를 조립한 뒤, 파일 객체로 만들어 delete()합니다.
        rootPath.resolve(att.getStoredName()).toFile().delete();
      }

      // 3. 데이터베이스에서 게시글 삭제 (JPA에서 cascade 처리에 의해 관련 데이터도 연쇄적으로 삭제되거나 고아가 됨)
      postService.deleteById(id);
    
      // 삭제 후 게시판 메인 목록 화면으로 리다이렉트합니다.
      return "redirect:/board";
  }
 
  /**
   * [게시글 수정 폼 화면 이동 API]
   */
  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable Long id, @ModelAttribute("page")  Integer page, Model model) {
      // 해당 게시글의 기존 작성 정보 데이터를 DB에서 찾아 꺼내옵니다.
      Post post = postService.findById(id);
      
      // write.html 폼과 똑같은 th:object 구조로 데이터를 바인딩하기 위해 form에 담아 넘깁니다.
      model.addAttribute("form", post);
      model.addAttribute("postId", id);
      System.out.println(page);
      
      // 글쓰기 화면인 write.html 템플릿을 재사용합니다. (postId 값 유무에 따라 '등록' 혹은 '수정' 처리로 화면이 다르게 표출됨)
      return "board/write";
  }

  /**
   * [게시글 수정 처리 API]
   * 
   * @Valid @ModelAttribute("form") PostFormDTO form: 사용자가 수정한 제목/내용을 검증하고 바인딩합니다.
   * @BindingResult bindingResult: 데이터 검증 유효성 에러를 수집합니다.
   */
  @PostMapping("/{id}/edit")
  public String edit(@Valid @ModelAttribute("form") PostFormDTO form,
      BindingResult bindingResult,@PathVariable Long id,
      @SessionAttribute(value = "loginMember", required = false) Member loginMember, RedirectAttributes attributes) {

      // 제목이나 본문 텍스트 조건 유효성 위반 에러가 있을 시 수정 폼 화면으로 다시 돌려보냅니다.
      if(bindingResult.hasErrors()) return "board/"+id+"/edit";
      
      // 권한 검증: 로그인이 만료되었을 경우 차단합니다.
      if(loginMember == null) return "redirect:/auth/login";
        
      // 서비스 레이어에 게시글 식별 ID와 수정 폼 정보, 작성자 회원 정보를 넘겨주어 변경 감지를 활용한 DB 수정을 진행합니다.
      postService.updatePost(id,form,loginMember);

      // 수정 작업이 성공적으로 종료되면, 수정한 해당 글의 상세페이지로 리다이렉트하여 바뀐 내용을 확인할 수 있게 합니다.
      return "redirect:/board/"+id;
  }
  
  
  
}
