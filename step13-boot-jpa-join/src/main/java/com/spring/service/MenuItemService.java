package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.entity.MenuItem;
import com.spring.repository.MenuItemRepository;

import jakarta.validation.Valid;

@Service
@Transactional(readOnly =  true)
public class MenuItemService {
	
	private final MenuItemRepository menuItemRepository;

	public MenuItemService(MenuItemRepository menuItemRepository) {
		this.menuItemRepository = menuItemRepository;
	}

	public List<MenuItem> findAll() {
		return menuItemRepository.findAll();
	}

	@Transactional
	public void save(MenuItem menuItem) {
		menuItemRepository.save(menuItem);
	}

	public MenuItem findById(Long id) {
		return menuItemRepository.findById(id).orElseThrow(
				() -> new IllegalArgumentException("찾으시는 메뉴 정보가 없습니다."));
	}

	@Transactional
	public void update(MenuItem menu) {
		MenuItem raw = findById(menu.getId());
		raw.setName(menu.getName());
		raw.setCategory(menu.getCategory());
		raw.setAvailable(menu.isAvailable());
		raw.setPrice(menu.getPrice());
		
	}

	@Transactional
	public void delete(Long id) {
		menuItemRepository.deleteById(id);
	}
	
	

}
