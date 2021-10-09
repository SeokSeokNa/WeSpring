package com.nh.controller;

import com.nh.aop.LogExecutionTime;
import com.nh.dto.BoardDto;
import com.nh.dto.UserDto;
import com.nh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup/new")
    public String getSignup() {
        return "user/signup"; //jsp 파일명
    }
    //get는 페이지간 이동 또는 조회(회원목록, 게시판 목록 조회 등)

    //post는 값을 저장할 때
    @PostMapping("/signup/new")
    @ResponseBody
    public String postSignup(UserDto userDto) {
        String result_val = "";
        System.out.println("아이디 = " + userDto.getUserId());
        System.out.println("비밀번호 = " + userDto.getUserPass());
        System.out.println("이름 = " + userDto.getUserName());
        System.out.println("성별 = " + userDto.getGender());
        int result = userService.insertUser(userDto);
        if (result > 0) {
            result_val = "ok";
        } else {
            result_val = "no";
        }
        return result_val;
    }


  /*  @LogExecutionTime
    @RequestMapping("/user/{id}")
    public String login(@PathVariable String id, Model model) {
        UserDto userDto = userService.selectUser(id);
        System.out.println("이름 = " + userDto.getUserName());
        model.addAttribute("user" , userDto);
        return "user/user";
    }*/

    //전체유저 조회 (연습용)
    @RequestMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.allUsers();
        for (UserDto user : users) {
            System.out.println("사람 이름 = " + user.getUserName());
        }
        model.addAttribute("users", users);
        return "user/users";
    }

    @ResponseBody  //응답 어노테이션
    @PostMapping("/signin/new")
    public String singIn(@RequestParam("userId") String userId, @RequestParam("userPass") String userPass, HttpSession session) {
        System.out.println("아이디 = " + userId);
        System.out.println("비번 = " + userPass);

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("userId", userId);
        map.put("userPass", userPass);
        UserDto result = userService.selectUser(map);

        if (result != null) {
            session.setAttribute("user_info", result);
            return "ok";
        } else {
            return "no";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @ResponseBody
    @PostMapping("/idCheck")
    public String idCheck(@RequestParam("userId") String userId) {
        String return_val = "";
        int result = userService.idCheck(userId);

        if (result == 1) {
            return_val =  "ok";
        } else if (result == 0) {
            return_val =  "no";
        }
       return return_val;
    }
}
