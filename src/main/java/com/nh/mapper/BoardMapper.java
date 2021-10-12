package com.nh.mapper;

import com.nh.dto.BoardDto;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface BoardMapper {
    public BoardDto selectBoard(String id);

    public void insertBoard(BoardDto boardDto);

    public List<BoardDto> boardList(BoardDto boardDto);

    public BoardDto getBoardContents(int num);

    public void updateBoard(BoardDto boardDto);

    public void getBoardDelete(int boardDto);

    public int totalBoardCount(BoardDto boardDto);

}
