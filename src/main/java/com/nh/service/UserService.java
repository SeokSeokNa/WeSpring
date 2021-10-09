package com.nh.service;

import com.nh.dto.UserDto;

import java.util.HashMap;
import java.util.List;


public interface UserService {
    /*
        service를 인터페이스로 구현한 이유
         - 삼성디프 판매서비스 와 애플 판매서비스가 있을때 현재 삼성디프 서비스를 사용하고 있다가 애플 판매서비스로 바꿔야한다면
           controller에서는 여전히 똑같은 이름에 인터페이스만 바라보고 똑같은 이름에 판매서비스 메소드만 호출하면 되기에 유지보수성이 강해진다.
    *

    */
    //유저조회기능
    public UserDto selectUser(HashMap<String, String> map);

    //모든 유저 조회기능
    public List<UserDto> allUsers();

    //회원가입
    public int insertUser(UserDto userDto);

    public int idCheck(String userId);

}