package com.spring.constructor;

// 주문 정보를 저장하는 역할만 맡는 저장소 객체.
public class OrderRepository {
	public void save(String orderInfo) {
		System.out.println("[OrderRepository] 주문 저장 : "+orderInfo);
	}
}
