package com.nh.mapper;

import com.nh.dto.UserDto;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {
    public UserDto selectUser(HashMap<String, String> map);

    public List<UserDto> allUsers();

    public int insertUser(UserDto userDto);

    public int idCheck(String userId);
}
