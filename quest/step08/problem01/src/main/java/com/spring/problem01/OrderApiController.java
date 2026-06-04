package com.spring.problem01;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문 관리 REST API 컨트롤러
 *
 * @RestController : @Controller + @ResponseBody 의 조합 어노테이션.
 *   반환값을 View로 전달하지 않고, JSON 형태로 HTTP 응답 바디에 직접 씁니다.
 *
 * @RequestMapping("/api/orders") : 이 컨트롤러의 모든 요청 경로 앞에 /api/orders 가 붙습니다.
 */
// TODO 1: @RestController 어노테이션을 추가하라.
// TODO 2: @RequestMapping("/api/orders") 를 추가하라.
@RestController
@RequestMapping("/api/orders")
public class OrderApiController {

    /**
     * 주문 목록을 메모리에 저장하는 리스트 (임시 저장소, DB 대신 사용)
     * List.of()는 불변(immutable) 리스트를 반환하므로,
     * new ArrayList<>()로 감싸서 이후 add/remove 가 가능하도록 합니다.
     */
    private final List<OrderDto> orders = new ArrayList<>(List.of(
        new OrderDto(1L, "아메리카노", 2, 8000),
        new OrderDto(2L, "치즈케이크", 1, 5500)
    ));

    /**
     * 전체 주문 목록 조회
     * GET /api/orders → orders 리스트를 JSON 배열로 반환
     *
     * @GetMapping : HTTP GET 메서드와 이 메서드를 연결합니다.
     *              경로를 생략하면 클래스의 @RequestMapping 경로("/api/orders")와 동일합니다.
     */
    // TODO 3: GET /api/orders 요청 시 orders 목록을 JSON으로 반환하는 메서드를 작성하라.
    @GetMapping
    public List<OrderDto> orderList() {
        return orders; // 리스트 전체를 JSON 배열로 반환
    }

    /**
     * 특정 주문 단건 조회
     * GET /api/orders/{id} → 해당 id의 주문을 JSON으로 반환
     *
     * @GetMapping("/{id}") : URL 경로의 {id} 부분을 변수로 받습니다.
     * @PathVariable("id")  : URL 경로 변수 {id}를 메서드 파라미터 id에 바인딩합니다.
     *
     * Stream API 설명:
     *   orders.stream()          → 리스트를 스트림(연속된 데이터 흐름)으로 변환
     *   .filter(item -> ...)     → 조건에 맞는 항목만 통과시킴
     *   .findFirst()             → 첫 번째로 일치하는 항목을 Optional로 반환
     *   .orElse(...)             → Optional이 비어있으면(일치하는 항목 없으면) 기본값 반환
     */
    // TODO 4: GET /api/orders/{id} 요청 시 해당 id의 OrderDto를 JSON으로 반환하는 메서드를 작성하라.
    @GetMapping("/{id}")
    public OrderDto selectOrder(@PathVariable("id") Long id) {
        return orders.stream()
                .filter(item -> item.getOrderId() == id) // id가 일치하는 주문 탐색
                .findFirst()
                .orElse(new OrderDto(-1L, "주문내역없음", 0, 0)); // 없으면 기본 응답 반환
    }
}
