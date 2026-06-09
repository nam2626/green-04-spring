package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.mapper.MenuMapper;

@Service
public class MenuService {
    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    
}
