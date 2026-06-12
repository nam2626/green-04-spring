package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.Order;
import com.spring.entity.OrderItem;
import com.spring.entity.MenuItem;
import com.spring.entity.OrderStatus;
import com.spring.repository.MemberRepository;
import com.spring.repository.MenuItemRepository;
import com.spring.repository.OrderRepository;

/**
 * 주문 서비스 클래스
 * 복합적인 주문 생성 로직 및 상태 변경을 담당합니다.
 */
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

	/**
	 * 조건 검색을 통한 주문 목록 조회
	 */
	public List<Order> search(Long memberId, OrderStatus status) {
		return orderRepository.search(memberId,status);
	}

	/**
	 * ★ 주문 생성 핵심 로직
	 * 1. 회원 정보 확인
	 * 2. 주문 상품들 정보를 바탕으로 OrderItem 생성 및 연관관계 설정
	 * 3. 주문(Order) 저장 (Cascade 설정으로 OrderItem도 함께 저장됨)
	 */
	@Transactional
	public Order save(Long memberId, List<Long> menuItemIds, List<Integer> quantities) {
		Order order = new Order();
		
		// 1. 주문 회원 조회
		order.setMember(memberRepository.findById(memberId)
				.orElseThrow(() -> new IllegalArgumentException("주문하려는 회원이 없습니다.")));
		
		// 2. 주문 상세(OrderItem) 구성
		for (int i = 0; i < menuItemIds.size(); i++) {
			if(quantities.get(i) == 0) continue; // 수량이 0인 메뉴는 제외
			
			MenuItem item = menuItemRepository.findById(menuItemIds.get(i))
					.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));
			
			// 주문 당시의 가격과 수량을 담은 상세 내역 생성 및 추가
			order.addOrderItem(new OrderItem(item, quantities.get(i), item.getPrice()));
		}
		
		// 최소 1개 이상의 메뉴가 있어야 함
		if(order.getOrderItems().isEmpty()) {
			throw new IllegalArgumentException("메뉴를 1개 이상 선택하세요.");
		}
		
		return orderRepository.save(order);
	}

	/**
	 * 주문 단일 조회
	 */
	public Order findById(Long id) {
		return orderRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 주문 정보가 없습니다."));
	}

	/**
	 * 주문 상태 변경 (대기중 -> 확정 등)
	 */
	@Transactional
	public void changeStatus(Long id, OrderStatus status) {
		Order order = findById(id);
		order.setStatus(status);
	}

	/**
	 * 주문 내역 삭제
	 */
	@Transactional
	public void delete(Long id) {
		orderRepository.deleteById(id);
	}
	
}
