package com.spring.problem04;

/**
 * 메뉴 데이터 전송 객체(DTO: Data Transfer Object) [완성된 파일 — 수정 불필요]
 *
 * DTO 란?
 *   계층 간(Controller ↔ Service ↔ View) 데이터를 주고받을 때 사용하는
 *   순수한 데이터 운반용 객체입니다. 비즈니스 로직을 포함하지 않습니다.
 *
 * Jackson 직렬화 규칙:
 *   스프링은 Jackson 라이브러리를 사용해 Java 객체 ↔ JSON 변환을 수행합니다.
 *   이를 위해 ① 기본 생성자(인자 없는 생성자)와 ② 각 필드의 getter 가 반드시 필요합니다.
 */
public class MenuDto {

    private Long id;        // 메뉴 고유 식별자
    private String name;    // 메뉴 이름 (예: "아메리카노")
    private int price;      // 메뉴 가격 (단위: 원)
    private boolean available; // 판매 가능 여부 (true: 판매 중, false: 품절)

    // Jackson이 JSON → Java 역직렬화 시 이 기본 생성자를 사용합니다. (필수!)
    public MenuDto() {}

    // 초기 데이터 세팅 등 편의를 위한 전체 필드 생성자
    public MenuDto(Long id, String name, int price, boolean available) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.available = available;
    }

    // --- Getter/Setter ---
    // Jackson이 직렬화(Java→JSON) 시 getXxx() 메서드를 호출하여 JSON 키를 만듭니다.

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    // boolean 타입 getter는 get 대신 is 로 시작하는 것이 관례입니다.
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
