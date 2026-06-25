package com.spring.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.spring.dto.BoardCommentDTO;

@Mapper
public interface BoardCommentMapper {

  void insertBoardComment(BoardCommentDTO comment);

  BoardCommentDTO selectBoardComment(int cno);

  void deleteBoardComment(int cno);

  void updateBoardComment(BoardCommentDTO reqBoard);

}
