package com.nh.controller;

import com.nh.aop.LogExecutionTime;
import com.nh.dto.UserDto;
import com.nh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup/new") //조회
    public String getSignUp() {
        return "signup";
    }


    @PostMapping("/signup/new") //등록 , 수정 ,삭제 등 할때 쓰임
    public String postSignUp(UserDto userDto) {
        userService.insertUser(userDto);
        return "redirect:/";
    }


//    @LogExecutionTime
    @RequestMapping("/user/{id}") //get이랑 동일한듯
    public String login(@PathVariable String id, Model model) {
        UserDto userDto = userService.selectUser(id);
        System.out.println("이름 =" + userDto.getUserName());
        model.addAttribute("user" , userDto);
        return "user";
    }

    //전체유저 조회

    @RequestMapping("/users")
    public String users(Model model) {
        List<UserDto> users = userService.allUsers();
        for (UserDto user : users) {
            System.out.println("사람 이름 = " + user.getUserName());
        }
        model.addAttribute("users" , users);
        return "users";
    }

    @ResponseBody
    @PostMapping ("/signin/new")
    public String  signIn(@RequestParam(name = "userId") String userId , HttpSession session) {
        System.out.println("아이디 = " + userId);
        UserDto result = userService.selectUser(userId);


        if(result != null){
            session.setAttribute("user_info" , result);
            return "ok";
        } else {
            return "no";
        }

    }
}
