package com.nh.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PageDto {
    private int pageIndex; //현재 페이지
    private int pageUnit; //페이지 개수
    private int pageSize; //페이지 사이즈
    private int firstIndex; //첫번째 인덱스
    private int countPerPage; //한페이지당 게시되는 게시물 수
    private int totCnt; //총개수
    private int startData; //시작데이터
    private int endData; //종료데이터
    private boolean prev, next; //이전, 다음버튼

}
