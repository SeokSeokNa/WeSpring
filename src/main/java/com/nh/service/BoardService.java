package com.nh.service;

import com.nh.dto.BoardDto;
import com.nh.dto.UserDto;

import java.util.List;

public interface BoardService {

    public int insertBoard(BoardDto boardDto);
    public List<BoardDto> boardList(int offset);
    public int totalCount();
}
