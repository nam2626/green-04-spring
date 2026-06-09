package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.dto.MenuDTO;
import com.spring.mapper.MenuMapper;

@Service
public class MenuService {
    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuDTO> findAll() {
        return menuMapper.findAll();
    }
    
}
