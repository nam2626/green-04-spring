package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.dto.BoardDTO;

public interface BoardMapper {

  List<BoardDTO> selectBoardList(@Param("page") int page,
                                 @Param("size") int size);

  List<BoardDTO> searchBoardList(@Param("keyword") String keyword, 
                                 @Param("page") int page,
                                 @Param("size") int size);

  int boardCount();

}
