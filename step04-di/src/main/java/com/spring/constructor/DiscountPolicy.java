package com.spring.constructor;

// 할인 정책을 추상화한 인터페이스.
// 서비스는 구체 클래스가 아니라 이 타입에 의존하도록 만든다.
public interface DiscountPolicy {
	int discount(int amount);
}
