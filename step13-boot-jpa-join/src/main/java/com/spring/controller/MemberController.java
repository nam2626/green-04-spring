package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.Member;
import com.spring.service.MemberService;

import jakarta.validation.Valid;

/**
 * 회원 관리 컨트롤러
 * 웹 브라우저의 요청을 받아 서비스에 전달하고, 결과를 뷰(HTML)에 전달합니다.
 */
@Controller
@RequestMapping("/members")
public class MemberController {
	
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	/**
	 * 회원 목록 페이지 조회
	 * ModelAndView: 데이터(Model)와 이동할 뷰 이름(View)을 한꺼번에 담는 객체
	 */
	@GetMapping
	public ModelAndView list(ModelAndView view) {
		view.addObject("members", memberService.findAll());
		view.setViewName("member/list");
		return view;
	}
	
	/**
	 * 회원 상세 정보 조회
	 */
	@GetMapping("/{id}")
	public ModelAndView detail(@PathVariable Long id, ModelAndView view) {
		view.addObject("member", memberService.findById(id));
		view.setViewName("member/detail");
		return view;
	}
	
	/**
	 * 회원 등록 폼 페이지 요청
	 */
	@GetMapping("/new")
	public ModelAndView form(ModelAndView view) {
		view.addObject("member", new Member()); // 빈 객체를 전달하여 폼 바인딩 준비
		view.setViewName("member/form");
		return view;
	}
	
	/**
	 * 회원 등록 실행
	 * @Valid: 입력값 유효성 검사 실행 (엔티티의 @NotBlank 등 체크)
	 * BindingResult: 유효성 검사 결과(에러 정보 등)가 담김
	 */
	@PostMapping
	public String save(@Valid Member member, BindingResult br, RedirectAttributes ra) {
		if(br.hasErrors()) { // 에러가 있으면 다시 등록 폼으로 이동
			return "member/form";
		}
		memberService.save(member);
		ra.addFlashAttribute("message", "회원 등록이 완료되었습니다."); // 리다이렉트 후 한 번만 보여줄 메시지
		return "redirect:/members";
	}
	
	/**
	 * 회원 수정 폼 페이지 요청
	 */
	@GetMapping("/{id}/edit")
	public ModelAndView edit(ModelAndView view, @PathVariable Long id) {
		view.addObject("member", memberService.findById(id));
		view.setViewName("member/form");
		return view;
	}
	
	/**
	 * 회원 수정 실행
	 */
	@PostMapping("/{id}/edit")
	public String update(@Valid @ModelAttribute("member") Member member, 
				BindingResult br, RedirectAttributes ra, @PathVariable Long id) {
		if(br.hasErrors()) {
			return "member/form";
		}
		member.setId(id);
		memberService.update(member);
		ra.addFlashAttribute("message", "회원 정보가 수정되었습니다.");
		return "redirect:/members/" + id;
	}
	
	/**
	 * 회원 삭제 실행
	 */
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes ra) {
		memberService.delete(id);
		ra.addFlashAttribute("message", "회원이 성공적으로 삭제되었습니다.");
		return "redirect:/members";
	}
	
}
