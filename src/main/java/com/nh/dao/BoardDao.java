package com.nh.dao;

import com.nh.dto.BoardDto;
import com.nh.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private SqlSession sqlSession;

    public BoardDto selectMember(final String id) {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        BoardDto boardDto = mapper.selectBoard(id);
        return boardDto;
    }

    public void insertBoard(BoardDto boardDto) {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.insertBoard(boardDto);
    }

    public List<BoardDto> boardList(BoardDto boardDto) {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        List<BoardDto> boardDtoList = mapper.boardList(boardDto);
        return boardDtoList;
    }

    public BoardDto getBoardContents(int num) {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.getBoardContents(num);
    }

    public void updateBoard(BoardDto boardDto){
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.updateBoard(boardDto);
    }
    public void getBoardDelete(int num){
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        mapper.getBoardDelete(num);
    }

    public int totalBoardCount(BoardDto boardDto) {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.totalBoardCount(boardDto);
    }
}
