package com.nh.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

    private int pageNo;
    private int boardNum;
    private String userId;
    private String title;
    private String content;
    private String writeDate;
    private int hit;
}
