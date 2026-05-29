package com.spring.constructor;

// 정해진 금액만큼 할인하는 구현체.
public class FixedDiscountPolicy implements DiscountPolicy{
	private final int discountAmount;
	
	// XML의 <constructor-arg value="1000"/> 값이 이 생성자 인자로 들어온다.
	public FixedDiscountPolicy(int discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Override
	public int discount(int amount) {
		int result = Math.min(amount, discountAmount);
		System.out.println("[FixedDiscountPolicy] 할인금액 : "+ result);
		return result;
	}

}
