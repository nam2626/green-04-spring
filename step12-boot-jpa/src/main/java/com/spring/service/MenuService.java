package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.MenuDTO;
import com.spring.repository.MenuRepository;



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

}





