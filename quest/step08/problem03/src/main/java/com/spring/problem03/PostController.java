package com.spring.problem03;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // TODO 1: GET /post/list → model에 "posts" 추가, "post/list" 반환
    //   @GetMapping("/list") 추가

    // TODO 2: GET /post/write → "post/write" 반환
    //   @GetMapping("/write") 추가

    // TODO 3: POST /post/write → @RequestParam으로 title, content, author 받아서
    //   postRepository.save(new Post(null, title, content, author)) 후
    //   "redirect:/post/list" 반환
    //   @PostMapping("/write") 추가

    // TODO 4: GET /post/detail?id=N → model에 "post" 추가, "post/detail" 반환
    //   @GetMapping("/detail") 추가, @RequestParam Long id

    // TODO 5: POST /post/delete?id=N → deleteById 후 "redirect:/post/list" 반환
    //   @PostMapping("/delete") 추가, @RequestParam Long id
}
