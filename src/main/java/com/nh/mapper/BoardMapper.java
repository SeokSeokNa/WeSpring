package com.nh.mapper;

import com.nh.dto.BoardDto;
import com.nh.dto.UserDto;

import java.util.List;

public interface BoardMapper {

    public int insertBoard(BoardDto boardDto);

    public List<BoardDto> boardList();

    public List<BoardDto> boardList2(int offset);

    public int totalCount();
}
