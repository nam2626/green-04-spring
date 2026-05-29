package com.spring.constructor;

public class KioskOrderService {
	// final 필드는 생성자에서 반드시 값이 들어와야 하므로 누락을 빨리 발견할 수 있다.
	private final OrderRepository orderRepository;
	private final DiscountPolicy discountPolicy;

	// 생성자 주입: 객체가 만들어질 때 필요한 의존성을 한 번에 전달받는다.
	public KioskOrderService(OrderRepository orderRepository, DiscountPolicy discountPolicy) {
		this.orderRepository = orderRepository;
		this.discountPolicy = discountPolicy;
	}
	
	public void order(String menuName, int quantity, int amount) {
		orderRepository.save(menuName + " X " + quantity);
		int discountAmount = discountPolicy.discount(amount);
		System.out.println("[KioskOrderService] 결제 예정 금액 : " 
												+ (amount - discountAmount));
	}
	
}

