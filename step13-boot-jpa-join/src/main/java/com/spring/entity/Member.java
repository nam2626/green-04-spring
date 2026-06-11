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

@Entity
@Table(name="member")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	@NonNull
	@NotBlank(message = "이름을 반드시 입력하세요")
	private String name;
	
	@Column(nullable = false, length = 100, unique = true)
	@NonNull
	@NotBlank(message = "이메일을 반드시 입력하세요")
	@Email(message = "이메일 형식이 아닙니다.")
	private String email;
	
	
	@Column(nullable = false, length = 100)
	@NonNull
	private String phone;
	
	@Column(name = "created_At", updatable = false)
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();
	
}








