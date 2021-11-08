package com.nh.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private String userName;
    private String userId;
    private Long expireDate;



    public TokenResponseDto(String accessToken, String refreshToken, String tokenType, String userName, String userId, Long expireDate) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.userName = userName;
        this.userId = userId;
        this.expireDate = expireDate;
    }
}
