package com.spring.problem04.controller;

import com.spring.problem04.entity.Product;
import com.spring.problem04.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/products")
public class ProductController {

    // TODO 10: ProductService 생성자 주입

    // TODO 11: GET /products → 전체 목록을 model("products")에 담아 "product/list" 반환
    @GetMapping
    public String list(Model model) {
        return "product/list";
    }

    // TODO 12: GET /products/{id} → 단건 조회, "product/detail" 반환
    //          없으면 IllegalArgumentException 발생
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        return "product/detail";
    }

    // TODO 13: GET /products/new → new Product() 를 model("product")에 담아 "product/form" 반환
    @GetMapping("/new")
    public String newForm(Model model) {
        return "product/form";
    }

    // TODO 14: POST /products → @Valid 유효성 검사
    //          오류 시 "product/form" 반환, 성공 시 redirect:/products
    @PostMapping
    public String create(@Valid @ModelAttribute("product") Product product,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        return "redirect:/products";
    }

    // TODO 15: GET /products/{id}/edit → 수정 폼 반환
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        return "product/form";
    }

    // TODO 16: POST /products/{id}/edit → 수정 처리
    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("product") Product form,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        return "redirect:/products";
    }

    // TODO 17: POST /products/{id}/delete → 삭제 처리 후 redirect:/products
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        return "redirect:/products";
    }
}
