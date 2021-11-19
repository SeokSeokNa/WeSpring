package com.nh.dto.naver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverTokenDto {

    String access_token;
    String refresh_token;
    String token_type;
    Integer expires_in;
    String error;
    String error_description;
}
