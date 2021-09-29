package com.nh.dao;

import com.nh.dto.BoardDto;
import com.nh.dto.UserDto;
import com.nh.mapper.BoardMapper;
import com.nh.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private SqlSession sqlSession;

    public int insertBoard(BoardDto boardDto) {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.insertBoard(boardDto);
    }

    public List<BoardDto> boardList(int offset , int pageData){
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.boardList2(offset,pageData);
    }


    public int totalCount() {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.totalCount();
    }

}
