package com.spring.problem01;

/**
 * 주문 데이터 전송 객체(DTO: Data Transfer Object)
 *
 * DTO 란?
 *   데이터를 계층 간에 전달할 때만 쓰는 순수 데이터 객체입니다.
 *   비즈니스 로직 없이 필드 + getter/setter 만 포함합니다.
 *
 * Jackson 직렬화 필수 조건:
 *   ① 기본 생성자(인자 없는 생성자) 존재
 *   ② 각 필드에 대한 getter 메서드 존재
 *   → 이 두 가지가 있어야 Java 객체 ↔ JSON 변환이 가능합니다.
 */
public class OrderDto {

    private Long orderId;     // 주문 고유 번호
    private String menuName;  // 주문 메뉴 이름
    private int quantity;     // 수량
    private int totalPrice;   // 총 가격 (단위: 원)

    // Jackson이 JSON → Java 역직렬화 시 사용하는 기본 생성자 (필수!)
    public OrderDto() {}

    // 초기 데이터 등록 등 편의를 위한 전체 필드 생성자
    public OrderDto(Long orderId, String menuName, int quantity, int totalPrice) {
        this.orderId = orderId;
        this.menuName = menuName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // --- Getter/Setter ---
    // Jackson이 Java → JSON 직렬화 시 getXxx() 를 호출하여 JSON 키 이름을 결정합니다.
    // 예: getOrderId() → JSON 키 "orderId"

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public String getMenuName() { return menuName; }
    public void setMenuName(String menuName) { this.menuName = menuName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice(int totalPrice) { this.totalPrice = totalPrice; }
}
