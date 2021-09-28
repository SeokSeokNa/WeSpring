package com.nh.dao;

import com.nh.dto.UserDto;
import com.nh.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    /*
        DAO(Data Access Object) 란?
         - 데이터베이스의 data에 접근하기 위한 객체!(데이터를 조회하거나 조작할때)
         - 데이터베이스에 접근할때마다 커넥션을 생성하고 PrepareStatement를 만들고 ,SQL을 작성해야한다.
           이러한 복잡성을 해결하기 위해 객체지향 프로그래밍 방법을 이용한 것이다!
         - 프로그램에서 사용할 DB로직을 객체 하나에 메소드로 구현해 놓고 필요할때마다 메소드만 호출해서 가져가는것!

         * DAO는 기본적으로 커넥션풀을 가지고 있으며 DB에 접근하려는 요청이 올때마다
           커넥션을 풀에서 꺼내서 할당해주고 작업이 끝나면 반납 받는다.
            - 커넥션이 부족한경우 앞에 사용중인 커넥션이 반환될때까지 기다려야한다...
              (커넥션풀을 크게하면 메모리소모가 큰대신 대기시간이 줄것이고 작게하면 메모리소모는 작은대신 대기시간이 길것이다 , 서버 성능에따라 전략을 잘짜도록)

         * DAO 자체는 싱글톤 패턴으로 객체가 하나이지만 디비와 연결을 갖는 커넥션자체는 다수일수 있다.

     */
    @Autowired
    private SqlSession sqlSession;

    public UserDto selectMember(final String id) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserDto userDto = mapper.selectUser(id);
        return userDto;
    }

    public List<UserDto> allUsers() {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<UserDto> userDtos = mapper.allUsers();
        return userDtos;
    }

    public int insertUser(UserDto userDto) {
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.insertUser(userDto);
    }
}
