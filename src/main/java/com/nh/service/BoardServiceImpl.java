package com.nh.service;

import com.nh.dao.BoardDao;
import com.nh.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardDao boardDao;

    @Override
    public int insertBoard(BoardDto boardDto) {
        return boardDao.insertBoard(boardDto);
    }

    @Override
    public List<BoardDto> boardList(int offset , int pageData) {
        return boardDao.boardList(offset,pageData);
    }

    @Override
    public int totalCount() {
        return boardDao.totalCount();
    }
}
