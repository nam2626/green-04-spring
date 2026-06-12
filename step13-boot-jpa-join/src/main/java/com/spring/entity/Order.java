package com.spring.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 주문 엔티티 클래스
 * 회원(Member)이 여러 메뉴(MenuItem)를 주문한 정보를 담는 중심 테이블입니다.
 */
@Entity
@Table(name="orders") // MySQL에서 'order'는 예약어이므로 'orders'로 테이블명 지정 권장
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * @ManyToOne: N:1 관계 설정 (여러 주문은 1명의 회원에 속함)
	 * fetch = FetchType.LAZY: 지연 로딩 설정 (필요할 때만 회원 정보를 조회하여 성능 최적화)
	 * @JoinColumn: 외래키(FK) 컬럼 이름을 지정
	 */
	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;
	
	@Column(name = "order_date", updatable = false)
	private LocalDateTime orderDate; // 주문 일시
	
	/**
	 * @Enumerated(EnumType.STRING): Enum 값을 문자열 형태로 DB에 저장
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private OrderStatus status = OrderStatus.PENDING; // 주문 상태 (기본값: 대기중)
	
	/**
	 * @OneToMany: 1:N 관계 설정 (주문 1건은 여러 주문 상세 항목을 가짐)
	 * mappedBy = "order": OrderItem 엔티티의 order 필드에 의해 관리됨
	 */
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	/**
	 * @PrePersist: 엔티티가 영속화(DB에 저장)되기 직전에 실행
	 */
	@PrePersist
	public void prePersist() {
		this.orderDate = LocalDateTime.now(); // 저장 시점의 시간을 자동으로 기록
	}

	/**
	 * 비즈니스 로직: 주문의 총 금액 계산
	 * DB 컬럼으로 저장하지 않고 실시간으로 계산하여 반환
	 */
    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(item -> item.getUnitPrice() * item.getQuantity())
                .sum();
    }

    /**
     * 연관관계 편의 메서드
     * 주문과 주문 상세 항목을 양방향으로 연결해줍니다.
     */
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        item.setOrder(this);
    }
	
}
