package com.nh.service;

import com.nh.dto.BoardDto;
import java.util.List;


public interface BoardService {

    public BoardDto selectBoard(String id);

    public void insertBoard(BoardDto boardDto);

    //모든 목록 조회기능
    public List<BoardDto> boardList(BoardDto boardDto);

    public BoardDto getBoardContents(int num);

    public void updateBoard(BoardDto boardDto);

    //서비스는 무슨 기능인지만 명시를 해놓고 실질적은 Servicelmpl이 기능 수행을 함
    public BoardDto getBoardDelete(int num);

    public int totalBoardCount(BoardDto boardDto);
}
