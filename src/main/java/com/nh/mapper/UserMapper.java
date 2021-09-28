package com.nh.mapper;

import com.nh.dto.UserDto;

import java.util.List;

public interface UserMapper {
    public UserDto selectUser(String id);

    public List<UserDto> allUsers();

    public int insertUser(UserDto userDto);
}
