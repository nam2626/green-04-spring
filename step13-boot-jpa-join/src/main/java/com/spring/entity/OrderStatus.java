package com.spring.entity;

/**
 * 주문 상태를 관리하는 Enum 클래스
 */
public enum OrderStatus {
	PENDING("대기중"),
	CONFIRMED("확정"),
	COMPLETED("완료"),
	CANCELLED("취소");
	
	private final String label;

	private OrderStatus(String label) {
		this.label = label;
	}
	
	/**
	 * 뷰(Thymeleaf)에서 상태 이름을 한글로 보여주기 위한 메서드
	 */
	public String getLabel() {
		return label;
	}
	
}
