package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.spring.dto.MenuDTO;

@Mapper
public interface MenuMapper {
    public List<MenuDTO> findAll();
}





