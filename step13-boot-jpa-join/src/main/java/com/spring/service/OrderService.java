package com.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.repository.MemberRepository;
import com.spring.repository.MenuItemRepository;
import com.spring.repository.OrderRepository;

@Service
@Transactional(readOnly =  true)
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final MemberRepository memberRepository;
	private final MenuItemRepository menuItemRepository;

	public OrderService(OrderRepository orderRepository, MemberRepository memberRepository,
			MenuItemRepository menuItemRepository) {
		this.orderRepository = orderRepository;
		this.memberRepository = memberRepository;
		this.menuItemRepository = menuItemRepository;
	}
	
	
	
	
}






