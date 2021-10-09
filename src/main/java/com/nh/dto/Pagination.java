package com.nh.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pagination {
    private int pageNO; //현재 페이지 번호
    private int totalCount; //전체 게시물 수
    private int countPage; //한 페이지당 게시되는 게시물 수
    private int pageSize; //페이지 리스트에 게시되는 페이지 수
    private int firstPageNO; //페이지 리스트의 첫 페이지 번호
    private int lastPageNo; //페이지 리스트의 마지막 페이지 번호
    private int firstIndex; //페이징 sql의 조건절의 사용되는 시작rownum
    private boolean prev; //이전 페이지 여부
    private boolean next;  //다음 페이지 여부


    public int getFirstPageNO() {
        lastPageNo = (int)(Math.ceil(pageNO/10.0)) * 10;
        firstPageNO = lastPageNo - 9;
        return firstPageNO;
    }

    public int getLastPage(){
        lastPageNo = (int)(Math.ceil(getPageNO()/10.0)) * 10;
        int realEnd = (int)(Math.ceil((getTotalCount()*1.0) / getCountPage()));
        if (realEnd < lastPageNo){
            lastPageNo = realEnd;
        }
        return lastPageNo;
    }

    public int getFirstIndex(){
        firstIndex = (getPageNO() -1) * getCountPage();
        return firstIndex;
    }

    public boolean getPrev(){
        prev = getFirstIndex() > 1;
        return prev;
    }

    public boolean getNext(){
        int realEnd = (int)(Math.ceil((getTotalCount() * 1.0)/ getCountPage()));

        next = getLastPageNo() < realEnd;
        return next;
    }



    @Override
    public String toString() {
        return "Pagination{" +
                "pageNO=" + pageNO +
                ", totalCount=" + totalCount +
                ", countPage=" + countPage +
                ", pageSize=" + pageSize +
                ", firstPageNO=" + firstPageNO +
                ", lastPageNo=" + lastPageNo +
                ", firstIndex=" + firstIndex +
                ", prev=" + prev +
                ", next=" + next +
                '}';
    }
}
