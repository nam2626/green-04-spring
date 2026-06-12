package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.MenuItem;

/**
 * 메뉴 레포지토리 인터페이스
 */
public interface MenuItemRepository extends JpaRepository<MenuItem, Long>{
	
		/**
		 * 메뉴명 오름차순으로 전체 목록 조회
		 */
		List<MenuItem> findAllByOrderByNameAsc();
		
		/**
		 * 특정 카테고리에 해당하는 메뉴들 조회
		 */
		List<MenuItem> findByCategory(String category);
		
		/**
		 * 현재 판매 가능한(available = true) 메뉴들만 조회
		 */
		List<MenuItem> findByAvailableTrue();
		
}
