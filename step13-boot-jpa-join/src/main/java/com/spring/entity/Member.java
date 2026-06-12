package com.spring.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 회원 엔티티 클래스
 * @Entity: JPA가 관리하는 엔티티임을 선언 (DB의 테이블과 매핑됨)
 * @Table: 매핑될 테이블 이름을 지정
 */
@Entity
@Table(name="member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Member {
	
	@Id // PK(Primary Key) 필드임을 지정
	@GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 Auto Increment 사용
	private Long id;

	@Column(nullable = false, length = 50) // 테이블 컬럼 설정 (NULL 불가, 길이 50)
	@NonNull // Lombok: 필수 인자를 받는 생성자(@RequiredArgsConstructor)용
	@NotBlank(message = "이름을 반드시 입력하세요") // Validation: 공백 허용 안함
	private String name;
	
	@Column(nullable = false, length = 100, unique = true) // 유니크 제약조건 추가
	@NonNull
	@NotBlank(message = "이메일을 반드시 입력하세요")
	@Email(message = "이메일 형식이 아닙니다.") // Validation: 이메일 형식 체크
	private String email;
	
	@Column(nullable = false, length = 100)
	@NonNull
	private String phone;
	
	@Column(name = "created_At", updatable = false) // 생성일은 수정 불가 설정
	private LocalDateTime createdAt;
	
	/**
	 * @OneToMany: 1:N 관계 설정 (회원 1명은 여러 주문을 가질 수 있음)
	 * mappedBy = "member": 연관관계의 주인이 아닌 쪽임을 명시 (Order 엔티티의 member 필드와 매핑)
	 * cascade = CascadeType.ALL: 회원이 삭제되거나 저장될 때 연관된 주문들도 같이 처리
	 * orphanRemoval = true: 리스트에서 제거된 주문을 DB에서도 삭제
	 */
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();
	
}
