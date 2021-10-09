package com.nh.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDto {

    /*
        DTO(Data Transfer Object)란?
         - 계층간에 데이터 교환을 위한 Java Beans 를 말한다.
         - Repository 에서 Service로 Service에서 Controller로 계층간에 데이터를 교환한다는 의미이다.
         - Controller <-> Service <-> Repository(DAO)
         - VO(Value Object) 라고도 불린다.

     */

    private String userId;
    private String userPass;
    private String userName;
    private String gender;


}