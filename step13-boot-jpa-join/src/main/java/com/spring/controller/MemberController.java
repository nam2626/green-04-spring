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
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/members")
public class MemberController {
	private final MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping
	public ModelAndView list(ModelAndView view) {
		view.addObject("members", memberService.findAll());
		view.setViewName("member/list");
		return view;
	}
	
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable Long id, RedirectAttributes ra) {
		memberService.delete(id);
		ra.addFlashAttribute("message", "회원이 삭제되었습니다.");
		return "redirect:/members";
	}
	
	@GetMapping("/new")
	public ModelAndView form(ModelAndView view) {
		view.addObject("member", new Member());
		view.setViewName("member/form");
		return view;
	}
	
	@PostMapping
	public String save(@Valid Member member, BindingResult br, RedirectAttributes ra) {
		if(br.hasErrors()) {
			return "member/form";
		}
		memberService.save(member);
		ra.addFlashAttribute("message", "회원정보 추가 완료");
		return "redirect:/members";
	}
	
	@GetMapping("/{id}/edit")
	public ModelAndView edit(ModelAndView view, @PathVariable Long id) {
		view.addObject("member", memberService.findById(id));
		view.setViewName("member/form");
		return view;
	}
	
	@PostMapping("/{id}/edit")
	public String update(@Valid @ModelAttribute("member") Member member, 
				BindingResult br, RedirectAttributes ra, @PathVariable Long id) {
		if(br.hasErrors()) {
			return "member/form";
		}
		member.setId(id);
		memberService.update(member);
		ra.addFlashAttribute("message", "회원정보 수정 완료");
		return "redirect:/members/"+id;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detail(@PathVariable Long id, ModelAndView view) {
		Member member = memberService.findById(id);
		view.setViewName("member/detail");
		view.addObject("member", member);
		return view;
	}
	
}






















