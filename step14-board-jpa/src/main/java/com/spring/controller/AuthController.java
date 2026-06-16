package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private MemberService memberService;

  @GetMapping("/register")
  public String registerForm(Model model) {
      model.addAttribute("member", new Member)
      return "auth/register";
  }
  

}
