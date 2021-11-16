package com.nh.controller;

import com.amazonaws.services.xray.model.Http;
import com.nh.dao.UserDao;
import com.nh.dto.UserDto;
import com.nh.service.UserService;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
@TestPropertySource(locations="classpath:AwsCredentials.properties")
@Slf4j
public class UserControllerTest  {

    @Autowired
    private UserDao dao;
    @Autowired
    private UserService service;

    @Test
    public void selectUser() {
        HashMap<String, String> map = new HashMap<String, String>();

        map.put("userId", "a");
        map.put("userpass", "a");
        service.selectUser(map);

    }
}