package com.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.repository.MenuItemRepository;

@Service
@Transactional(readOnly =  true)
public class MenuItemService {
	
	private final MenuItemRepository menuItemRepository;

	public MenuItemService(MenuItemRepository menuItemRepository) {
		this.menuItemRepository = menuItemRepository;
	}
	
	

}
