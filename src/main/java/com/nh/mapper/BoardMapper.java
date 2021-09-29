package com.nh.mapper;

import com.nh.dto.BoardDto;
import com.nh.dto.UserDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BoardMapper {

    public int insertBoard(BoardDto boardDto);

    public List<BoardDto> boardList();

    public List<BoardDto> boardList2(@Param("offset") int offset, @Param("pageData")int pageData);
    //파라미터를 dto가 아닌 일반값 두개이상 보낼 시
    //@Param 어노테이션을 넣어야 mapper.xml에서 인식할 수있다!!!

    public int totalCount();
}
