package com.spring.problem04;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 메뉴 관리 REST API 컨트롤러
 *
 * @RestController = @Controller + @ResponseBody
 *   → 모든 메서드의 반환값이 JSON으로 직렬화되어 HTTP 응답 바디에 담깁니다.
 *   → 일반 @Controller처럼 View 이름을 반환하는 것이 아니라 데이터 자체를 반환합니다.
 *
 * @RequestMapping("/api/menus")
 *   → 이 컨트롤러의 모든 엔드포인트는 /api/menus 를 기본 경로로 가집니다.
 */
// TODO 1: @RestController 추가
// TODO 2: @RequestMapping("/api/menus") 추가
@RestController
@RequestMapping("/api/menus")
public class MenuApiController {

    /**
     * 메뉴 목록을 메모리에 저장하는 리스트 (DB 대신 사용하는 임시 저장소)
     * List.of(...)로 초기 데이터를 만든 뒤 new ArrayList<>()로 감싸서
     * 나중에 항목을 추가(add)할 수 있도록 합니다.
     * (List.of()가 반환하는 리스트는 불변(immutable)이라 add가 불가능)
     */
    private final List<MenuDto> menus = new ArrayList<>(List.of(
        new MenuDto(1L, "아메리카노", 4000, true),
        new MenuDto(2L, "카페라떼", 4500, true),
        new MenuDto(3L, "녹차라떼", 4500, false)
    ));

    /**
     * 전체 메뉴 목록 조회
     * GET /api/menus → List<MenuDto> 전체 반환 (JSON 배열)
     *
     * @GetMapping : HTTP GET 요청을 이 메서드와 매핑합니다.
     */
    // TODO 3: GET /api/menus → List<MenuDto> 전체 반환
    //   @GetMapping 추가, 반환 타입: List<MenuDto>
    @GetMapping
    public List<MenuDto> getAllMenus() {
        return menus; // menus 리스트 전체를 JSON으로 반환
    }

    /**
     * 판매 가능한 메뉴만 필터링하여 조회
     * GET /api/menus/available → available=true 인 메뉴만 반환
     *
     * 향상된 for문(for-each)으로 리스트를 순회하면서
     * isAvailable()이 true인 항목만 골라냅니다.
     */
    // TODO 4: GET /api/menus/available → available=true인 메뉴만 필터링하여 반환
    //   @GetMapping("/available")
    @GetMapping("/available")
    public List<MenuDto> getAvailableMenus() {
        List<MenuDto> availableMenus = new ArrayList<>();
        for (MenuDto menu : menus) {
            if (menu.isAvailable()) {      // available 필드가 true인 메뉴만 선택
                availableMenus.add(menu);
            }
        }
        return availableMenus;
    }

    /**
     * 새 메뉴 등록
     * POST /api/menus → 요청 바디(JSON)를 MenuDto로 받아 리스트에 추가 후 반환
     *
     * @RequestBody : HTTP 요청 바디의 JSON을 Java 객체(MenuDto)로 자동 변환합니다.
     *               (Jackson 라이브러리가 변환을 담당)
     *
     * 새 id는 현재 리스트 크기 + 1 로 설정합니다.
     * (실제 서비스에서는 DB의 AUTO_INCREMENT 또는 시퀀스를 사용합니다.)
     */
    // TODO 5: POST /api/menus → @RequestBody MenuDto 받아서 리스트에 추가 후 반환
    //   새 id는 menus.size() + 1L
    //   @PostMapping, @RequestBody MenuDto menuDto
    @PostMapping
    public MenuDto addMenu(@RequestBody MenuDto menuDto) {
        menuDto.setId((long) (menus.size() + 1)); // 새 ID 부여
        menus.add(menuDto);                        // 리스트에 추가
        return menuDto;                            // 저장된 메뉴를 JSON으로 반환
    }
}
