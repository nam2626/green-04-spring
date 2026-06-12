package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.MenuItem;
import com.spring.repository.MenuItemRepository;

/**
 * 메뉴 서비스 클래스
 * @Transactional(readOnly = true): 조회 작업 최적화
 */
@Service
@Transactional(readOnly =  true)
public class MenuItemService {
	
	private final MenuItemRepository menuItemRepository;

	public MenuItemService(MenuItemRepository menuItemRepository) {
		this.menuItemRepository = menuItemRepository;
	}

	/**
	 * 전체 메뉴 목록 조회
	 */
	public List<MenuItem> findAll() {
		return menuItemRepository.findAll();
	}

	/**
	 * 메뉴 등록
	 */
	@Transactional
	public void save(MenuItem menuItem) {
		menuItemRepository.save(menuItem);
	}

	/**
	 * 단일 메뉴 조회
	 */
	public MenuItem findById(Long id) {
		return menuItemRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("찾으시는 메뉴 정보가 없습니다."));
	}

	/**
	 * 메뉴 정보 수정
	 */
	@Transactional
	public void update(MenuItem menu) {
		MenuItem raw = findById(menu.getId());
		raw.setName(menu.getName());
		raw.setCategory(menu.getCategory());
		raw.setAvailable(menu.isAvailable());
		raw.setPrice(menu.getPrice());
	}

	/**
	 * 메뉴 삭제
	 */
	@Transactional
	public void delete(Long id) {
		menuItemRepository.deleteById(id);
	}
	
}
