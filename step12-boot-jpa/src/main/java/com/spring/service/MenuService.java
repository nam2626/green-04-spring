package com.spring.service;

import java.util.List;import javax.swing.event.MenuListener;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.MenuDTO;
import com.spring.repository.MenuRepository;

import jakarta.validation.Valid;



@Service
@Transactional(readOnly = true)
public class MenuService {
	private final MenuRepository menuRepository;

	public MenuService(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
	
	public List<MenuDTO> findAll(){
		return menuRepository.findAll();
	}

	public MenuDTO findById(Long id) {
		return menuRepository.findById(id).orElseThrow(() -> 
				new IllegalArgumentException("해당 메뉴를 찾을 수 없습니다."));
	}
	
	/**
     * 수정 — Dirty Checking 활용
     * findById()로 영속 상태 엔티티를 가져와 필드를 변경하면
     * 트랜잭션 종료 시 UPDATE SQL이 자동 실행됨 (save() 불필요)
     */
	@Transactional
	public MenuDTO update(MenuDTO menu) {
		MenuDTO raw = findById(menu.getId());
		raw.setName(menu.getName());
		raw.setCategory(menu.getCategory());
		raw.setPrice(menu.getPrice());
		raw.setDescription(menu.getDescription());
		raw.setAvailable(menu.isAvailable());
		// save() 호출 없이도 트랜잭션 종료 시 UPDATE 자동 실행!
		return raw;
	}

	@Transactional
	public void delete(Long id) {
		menuRepository.deleteById(id);
	}

	public List<MenuDTO> findByNameContaining(String keyword) {
		return menuRepository.findByNameContaining(keyword);
	}

	public List<MenuDTO>  findByCategoryContaining(String category) {
		return menuRepository.findByCategoryContaining(category);
	}

}





