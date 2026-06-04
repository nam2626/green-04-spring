package com.spring.problem04;

import java.util.ArrayList;
import java.util.List;

// TODO 1: @RestController 추가
// TODO 2: @RequestMapping("/api/menus") 추가
public class MenuApiController {

    private final List<MenuDto> menus = new ArrayList<>(List.of(
        new MenuDto(1L, "아메리카노", 4000, true),
        new MenuDto(2L, "카페라떼", 4500, true),
        new MenuDto(3L, "녹차라떼", 4500, false)
    ));

    // TODO 3: GET /api/menus → List<MenuDto> 전체 반환
    //   @GetMapping 추가, 반환 타입: List<MenuDto>

    // TODO 4: GET /api/menus/available → available=true인 메뉴만 필터링하여 반환
    //   @GetMapping("/available")

    // TODO 5: POST /api/menus → @RequestBody MenuDto 받아서 리스트에 추가 후 반환
    //   새 id는 menus.size() + 1L
}
