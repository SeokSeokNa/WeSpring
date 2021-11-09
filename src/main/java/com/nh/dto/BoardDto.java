package com.nh.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class BoardDto extends PageDto {
    private int num;         //게시글 번호
    private String title;    //제목
    private String content;  //내용
    private String id;       //작성자 id
    private String board_date; //작성일
    private String board_update; //수정일
    private String keyword;  //검색
    private String searchType;  //검색
    private int offset;
    private int limit;
    private String profileImg;



}
