package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.MenuItem;
import com.spring.entity.Order;
import com.spring.entity.OrderItem;
import com.spring.entity.OrderStatus;
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

	public List<Order> search(Long memberId, OrderStatus status) {
		return orderRepository.search(memberId,status);
	}

	public Order save(Long memberId, List<Long> menuItemIds, List<Integer> quantities) {
		Order order = new Order();
		
		//주문한 회원정보도 등록
		order.setMember(memberRepository.findById(memberId)
				.orElseThrow(() -> new IllegalArgumentException("주문할려는 회원이 없습니다.")));
		//주문 내용 등록
		for (int i = 0; i < menuItemIds.size(); i++) {
			// 주문 수량이 0이면 건너뜀
			if(quantities.get(i) == 0) continue;
			// 주문할 MenuItem 조회
			MenuItem item = menuItemRepository
					.findById(menuItemIds.get(i))
					.orElseThrow(() -> 
					new IllegalArgumentException("주문할려는 회원이 없습니다."));
			
			order.addOrderItem(new OrderItem(item, quantities.get(i), item.getPrice()));
		}
		
		if(order.getOrderItems().isEmpty()) {
			throw new IllegalArgumentException("메뉴를 1개이상 선택하세요.");
		}
		
		
		return orderRepository.save(order);
	}
	
	
	
	
}






