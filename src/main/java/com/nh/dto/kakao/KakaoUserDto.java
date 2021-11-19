package com.nh.dto.kakao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NaverUserDto {

    private int id;
    private String connected_at;
    private Properties properties;
    private KakaoAccount kakao_account;

}
