package com.nh.dto;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
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

//    public String getDate() {
//    SimpleDateFormat bdate = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
//        return bdate.format(board_date);
//}


    @Override
    public String toString() {
        return "BoardDto{" +
                "num=" + num +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", id='" + id + '\'' +
                ", board_date='" + board_date + '\'' +
                ", board_update='" + board_update + '\'' +
                ", keyword='" + keyword + '\'' +
                ", searchType='" + searchType + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                '}';
    }
}
