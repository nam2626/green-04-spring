package com.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spring.dto.BoardCommentDTO;

@Mapper
public interface BoardCommentMapper {

  void insertBoardComment(BoardCommentDTO comment);

}
