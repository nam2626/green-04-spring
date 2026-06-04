package com.spring.problem03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 게시글 CRUD 컨트롤러
 *
 * @Controller     : View 이름을 반환하는 일반 MVC 컨트롤러
 * @RequestMapping : 이 컨트롤러의 모든 경로는 /post 로 시작합니다.
 *
 * CRUD = Create(생성) / Read(조회) / Update(수정) / Delete(삭제)
 */
@Controller
@RequestMapping("/post")
public class PostController {

    /**
     * PostRepository를 생성자 주입(Constructor Injection) 방식으로 받습니다.
     * 필드 주입(@Autowired) 보다 생성자 주입이 권장됩니다.
     *   → 테스트 시 직접 객체를 주입하기 쉽고, 불변성(final)을 보장할 수 있습니다.
     */
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * 게시글 목록 조회
     * GET /post/list → 전체 게시글을 "posts" 키로 뷰에 전달
     *
     * ModelAndView : Model(데이터)과 View(화면 이름)을 함께 담는 객체입니다.
     *   addObject("키", 값) : JSP에서 ${posts} 로 접근할 데이터를 추가합니다.
     *   setViewName("경로") : 렌더링할 JSP 경로를 지정합니다.
     */
    // TODO 1: GET /post/list → model에 "posts" 추가, "post/list" 반환
    @GetMapping("/list")
    public ModelAndView boardList(ModelAndView view) {
        view.addObject("posts", postRepository.findAll()); // 전체 게시글을 모델에 추가
        view.setViewName("post/list");                     // WEB-INF/views/post/list.jsp
        return view;
    }

    /**
     * 게시글 작성 폼 표시
     * GET /post/write → write.jsp 렌더링 (빈 폼 화면)
     *
     * String 반환: @Controller에서 문자열을 반환하면 View 이름으로 처리됩니다.
     *              ModelAndView를 사용하지 않아도 됩니다.
     */
    // TODO 2: GET /post/write → "post/write" 반환
    @GetMapping("/write")
    public String writeView() {
        return "post/write"; // WEB-INF/views/post/write.jsp
    }

    /**
     * 게시글 저장 처리
     * POST /post/write → 폼 데이터를 Post 객체로 받아 저장 후 목록으로 리다이렉트
     *
     * Post post : 스프링이 HTTP 요청 파라미터(title, content, author)를
     *             Post 객체의 setter를 통해 자동으로 바인딩합니다.
     *             (HTML 폼의 name 속성과 Post 필드명이 일치해야 합니다.)
     *
     * "redirect:/post/list" : 저장 완료 후 목록 페이지로 이동 (PRG 패턴)
     *   → PRG(Post-Redirect-Get): 새로고침 시 폼이 중복 제출되는 문제를 방지합니다.
     */
    // TODO 3: POST /post/write → Post 객체로 받아서 저장 후 "redirect:/post/list" 반환
    @PostMapping("/write")
    public String writePost(Post post) {
        postRepository.save(post); // 게시글 저장 (id는 Repository에서 자동 부여)
        return "redirect:/post/list";
    }

    /**
     * 게시글 상세 조회
     * GET /post/detail/{id} → 해당 id의 게시글을 "post" 키로 뷰에 전달
     *
     * @PathVariable("id") long id : URL 경로의 {id} 값을 파라미터로 받습니다.
     *   예: GET /post/detail/3 → id = 3
     */
    // TODO 4: GET /post/detail/{id} → model에 "post" 추가, "post/detail" 반환
    @GetMapping("/detail/{id}")
    public ModelAndView detailPost(@PathVariable("id") long id, ModelAndView view) {
        view.addObject("post", postRepository.findById(id)); // 특정 게시글을 모델에 추가
        view.setViewName("post/detail");                     // WEB-INF/views/post/detail.jsp
        return view;
    }

    /**
     * 게시글 삭제 처리
     * POST /post/delete → 해당 id의 게시글 삭제 후 목록으로 리다이렉트
     *
     * Long id : HTML 폼의 hidden input name="id" 값이 자동으로 바인딩됩니다.
     *           (DELETE /post/{id} 같은 REST 방식 대신 HTML 폼 방식 사용)
     */
    // TODO 5: POST /post/delete?id=N → deleteById 후 "redirect:/post/list" 반환
    @PostMapping("/delete")
    public String deletePost(Long id) {
        postRepository.deleteById(id); // id에 해당하는 게시글 삭제
        return "redirect:/post/list";
    }
}
