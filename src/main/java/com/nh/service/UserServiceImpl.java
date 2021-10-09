package com.nh.service;

import com.nh.dao.UserDao;
import com.nh.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDto selectUser(HashMap<String, String> map) {
        UserDto userDto = userDao.selectMember(map);
        return userDto;
    }

    @Override
    public List<UserDto> allUsers() {
        List<UserDto> userDtos = userDao.allUsers();
        return userDtos;
    }

    @Override
    public int insertUser(UserDto userDto) {
        return  userDao.insertUser(userDto);
    }

    @Override
    public int idCheck(String userId) {
        return  userDao.idCheck(userId);
    }

}
