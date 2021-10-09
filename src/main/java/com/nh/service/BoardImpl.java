package com.nh.service;

import com.nh.dao.BoardDao;
import com.nh.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardImpl implements BoardService {

    @Autowired
    private BoardDao boardDao;

    @Override
    public BoardDto selectBoard(String id) {
        BoardDto boardDto = boardDao.selectMember(id);
        return boardDto;
    }

    @Override
    public void insertBoard(BoardDto boardDto) {
        boardDao.insertBoard(boardDto);
    }

    @Override
    public List<BoardDto> boardList(BoardDto boardDto) {
        List<BoardDto> boardDtoList = boardDao.boardList(boardDto);
        return boardDtoList;
    }

//    //페이징
//    @Override
//    public int getListCnt(BoardDto boardDto){
//      return boardDao.getListCnt(boardDto);
//    }


    @Override
    public BoardDto getBoardContents(int num) {
        return boardDao.getBoardContents(num);
//        아래와 같은 형태
//        BoardDto boardContents = boardDao.getBoardContents(num);
//        return boardContents;
    }

    @Override
    public void updateBoard(BoardDto boardDto) {
        boardDao.updateBoard(boardDto);
    }

    @Override
    public BoardDto getBoardDelete(int num){
        boardDao.getBoardDelete(num);
        return null;
    }


    @Override
    public int totalBoardCount(BoardDto boardDto) {
        return boardDao.totalBoardCount(boardDto);
    }
}

