package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.MemberDTO;
import com.spring.service.MemberService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/auth")
public class AuthController {
  @Autowired
  private MemberService memberService;

  @GetMapping("/register")
  public String registerForm(Model model) {
      model.addAttribute("form", new MemberDTO());
      return "auth/register";
  }
  
  @PostMapping("/register")
  public String register(@Valid @ModelAttribute("form") MemberDTO form, 
      BindingResult bindingResult, RequestAttributes requestAttributes) {
      
      return "redirect:/";
      // return  "redirect:/auth/login";
  }
  

}
